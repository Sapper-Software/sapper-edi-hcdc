package org.apache.hadoop.hdfs.server.namenode;

import ai.sapper.hcdc.agents.namenode.DFSAgentError;
import ai.sapper.hcdc.agents.namenode.model.DFSEditLogBatch;
import ai.sapper.hcdc.agents.namenode.model.DFSTransactionType;
import ai.sapper.hcdc.common.model.DFSTransaction;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.hadoop.hdfs.protocol.Block;


@Getter
@Accessors(fluent = true)
public class DFSEditLogParser {
    /*
          OP_ADD                        ((byte)  0, AddOp.class),
          // deprecated operation
          OP_RENAME_OLD                 ((byte)  1, RenameOldOp.class),
          OP_DELETE                     ((byte)  2, DeleteOp.class),
          OP_MKDIR                      ((byte)  3, MkdirOp.class),
          OP_SET_REPLICATION            ((byte)  4, SetReplicationOp.class),
          @Deprecated OP_DATANODE_ADD   ((byte)  5), // obsolete
          @Deprecated OP_DATANODE_REMOVE((byte)  6), // obsolete
          OP_SET_PERMISSIONS            ((byte)  7, SetPermissionsOp.class),
          OP_SET_OWNER                  ((byte)  8, SetOwnerOp.class),
          OP_CLOSE                      ((byte)  9, CloseOp.class),
          OP_SET_GENSTAMP_V1            ((byte) 10, SetGenstampV1Op.class),
          OP_SET_NS_QUOTA               ((byte) 11, SetNSQuotaOp.class), // obsolete
          OP_CLEAR_NS_QUOTA             ((byte) 12, ClearNSQuotaOp.class), // obsolete
          OP_TIMES                      ((byte) 13, TimesOp.class), // set atime, mtime
          OP_SET_QUOTA                  ((byte) 14, SetQuotaOp.class),
          // filecontext rename
          OP_RENAME                     ((byte) 15, RenameOp.class),
          // concat files
          OP_CONCAT_DELETE              ((byte) 16, ConcatDeleteOp.class),
          OP_SYMLINK                    ((byte) 17, SymlinkOp.class),
          OP_GET_DELEGATION_TOKEN       ((byte) 18, GetDelegationTokenOp.class),
          OP_RENEW_DELEGATION_TOKEN     ((byte) 19, RenewDelegationTokenOp.class),
          OP_CANCEL_DELEGATION_TOKEN    ((byte) 20, CancelDelegationTokenOp.class),
          OP_UPDATE_MASTER_KEY          ((byte) 21, UpdateMasterKeyOp.class),
          OP_REASSIGN_LEASE             ((byte) 22, ReassignLeaseOp.class),
          OP_END_LOG_SEGMENT            ((byte) 23, EndLogSegmentOp.class),
          OP_START_LOG_SEGMENT          ((byte) 24, StartLogSegmentOp.class),
          OP_UPDATE_BLOCKS              ((byte) 25, UpdateBlocksOp.class),
          OP_CREATE_SNAPSHOT            ((byte) 26, CreateSnapshotOp.class),
          OP_DELETE_SNAPSHOT            ((byte) 27, DeleteSnapshotOp.class),
          OP_RENAME_SNAPSHOT            ((byte) 28, RenameSnapshotOp.class),
          OP_ALLOW_SNAPSHOT             ((byte) 29, AllowSnapshotOp.class),
          OP_DISALLOW_SNAPSHOT          ((byte) 30, DisallowSnapshotOp.class),
          OP_SET_GENSTAMP_V2            ((byte) 31, SetGenstampV2Op.class),
          OP_ALLOCATE_BLOCK_ID          ((byte) 32, AllocateBlockIdOp.class),
          OP_ADD_BLOCK                  ((byte) 33, AddBlockOp.class),
          OP_ADD_CACHE_DIRECTIVE        ((byte) 34, AddCacheDirectiveInfoOp.class),
          OP_REMOVE_CACHE_DIRECTIVE     ((byte) 35, RemoveCacheDirectiveInfoOp.class),
          OP_ADD_CACHE_POOL             ((byte) 36, AddCachePoolOp.class),
          OP_MODIFY_CACHE_POOL          ((byte) 37, ModifyCachePoolOp.class),
          OP_REMOVE_CACHE_POOL          ((byte) 38, RemoveCachePoolOp.class),
          OP_MODIFY_CACHE_DIRECTIVE     ((byte) 39, ModifyCacheDirectiveInfoOp.class),
          OP_SET_ACL                    ((byte) 40, SetAclOp.class),
          OP_ROLLING_UPGRADE_START      ((byte) 41, RollingUpgradeStartOp.class),
          OP_ROLLING_UPGRADE_FINALIZE   ((byte) 42, RollingUpgradeFinalizeOp.class),
          OP_SET_XATTR                  ((byte) 43, SetXAttrOp.class),
          OP_REMOVE_XATTR               ((byte) 44, RemoveXAttrOp.class),
          OP_SET_STORAGE_POLICY         ((byte) 45, SetStoragePolicyOp.class),
          OP_TRUNCATE                   ((byte) 46, TruncateOp.class),
          OP_APPEND                     ((byte) 47, AppendOp.class),
          OP_SET_QUOTA_BY_STORAGETYPE   ((byte) 48, SetQuotaByStorageTypeOp.class),
          OP_ADD_ERASURE_CODING_POLICY  ((byte) 49, AddErasureCodingPolicyOp.class),
          OP_ENABLE_ERASURE_CODING_POLICY((byte) 50, EnableErasureCodingPolicyOp.class),
          OP_DISABLE_ERASURE_CODING_POLICY((byte) 51,
              DisableErasureCodingPolicyOp.class),
          OP_REMOVE_ERASURE_CODING_POLICY((byte) 52, RemoveErasureCodingPolicyOp.class),
     */

    public void parse(@NonNull FSEditLogOp op, @NonNull DFSEditLogBatch batch) throws DFSAgentError {
        if (op.opCode == FSEditLogOpCodes.OP_START_LOG_SEGMENT) {
            if (op.getTransactionId() != batch.startTnxId()) {
                throw new DFSAgentError(
                        String.format("Start transaction ID mismatch. [expected=%d][actual=%d]",
                                op.getTransactionId(), batch.startTnxId()));
            }
        } else if (op.opCode == FSEditLogOpCodes.OP_END_LOG_SEGMENT) {
            if (batch.isCurrent()) {
                batch.endTnxId(op.getTransactionId());
            } else {
                if (op.getTransactionId() != batch.endTnxId()) {
                    throw new DFSAgentError(
                            String.format("Start transaction ID mismatch. [expected=%d][actual=%d]",
                                    op.getTransactionId(), batch.endTnxId()));
                }
            }
        } else {
            switch (op.opCode) {
                case OP_ADD:
                    handleOpAdd(op, batch);
                    break;
                case OP_ADD_BLOCK:
                    handleOpAddBlock(op, batch);
                    break;
                case OP_APPEND:
                    handleOpAppend(op, batch);
                    break;
                case OP_UPDATE_BLOCKS:
                    handleOpUpdateBlocks(op, batch);
                    break;
                case OP_DELETE:
                    handleOpDelete(op, batch);
                    break;
                case OP_TRUNCATE:
                    handleOpTruncate(op, batch);
                    break;
                case OP_CLOSE:
                    handleOpClose(op, batch);
                    break;
            }
        }
    }

    private void handleOpClose(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.CloseOp) {
            FSEditLogOp.CloseOp cop = (FSEditLogOp.CloseOp) op;
            DFSTransactionType.DFSCloseFileType cft = new DFSTransactionType.DFSCloseFileType();

            cft.id(cop.txid);
            cft.op(DFSTransaction.Operation.CLOSE)
                    .timestamp(cop.mtime);
            cft.file(new DFSTransactionType.DFSFileType()
                    .path(cop.path)
                    .inodeId(cop.inodeId));
            cft.length(cop.length)
                    .blockSize(cop.blockSize)
                    .modifiedTime(cop.mtime)
                    .accessedTime(cop.atime)
                    .overwrite(cop.overwrite);

            if (cop.blocks != null && cop.blocks.length > 0) {
                for (int ii = 0; ii < cop.blocks.length; ii++) {
                    DFSTransactionType.DFSBlockType bt = new DFSTransactionType.DFSBlockType();
                    bt.blockId(cop.blocks[ii].getBlockId());
                    bt.size(cop.blocks[ii].getNumBytes());
                    bt.generationStamp(cop.blocks[ii].getGenerationStamp());

                    cft.blocks().add(bt);
                }
            }
            batch.transactions().add(cft);

        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.CloseOp.class, op.getClass()));
        }
    }

    private void handleOpTruncate(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.TruncateOp) {
            FSEditLogOp.TruncateOp top = (FSEditLogOp.TruncateOp) op;
            DFSTransactionType.DFSTruncateBlockType tr = new DFSTransactionType.DFSTruncateBlockType();

            tr.id(top.txid)
                    .op(DFSTransaction.Operation.TRUNCATE)
                    .timestamp(top.timestamp);
            tr.file(new DFSTransactionType.DFSFileType().path(top.src));
            tr.block(new DFSTransactionType.DFSBlockType()
                    .blockId(top.truncateBlock.getBlockId())
                    .size(top.truncateBlock.getNumBytes())
                    .generationStamp(top.truncateBlock.getGenerationStamp()));
            tr.newLength(top.newLength);

            batch.transactions().add(tr);
        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.TruncateOp.class, op.getClass()));
        }
    }

    private void handleOpDelete(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.DeleteOp) {
            FSEditLogOp.DeleteOp dop = (FSEditLogOp.DeleteOp) op;
            DFSTransactionType.DFSDeleteFileType del = new DFSTransactionType.DFSDeleteFileType();

            del.id(dop.txid)
                    .op(DFSTransaction.Operation.DELETE)
                    .timestamp(dop.timestamp);
            del.file(new DFSTransactionType.DFSFileType().path(dop.path));

            batch.transactions().add(del);
        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.DeleteOp.class, op.getClass()));
        }
    }

    private void handleOpUpdateBlocks(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.UpdateBlocksOp) {
            FSEditLogOp.UpdateBlocksOp ubop = (FSEditLogOp.UpdateBlocksOp) op;
            DFSTransactionType.DFSUpdateBlocksType upd = new DFSTransactionType.DFSUpdateBlocksType();

            upd.id(ubop.txid).op(DFSTransaction.Operation.UPDATE_BLOCKS);
            upd.file(new DFSTransactionType.DFSFileType().path(ubop.path));
            if (ubop.blocks != null && ubop.blocks.length > 0) {
                for (int ii = 0; ii < ubop.blocks.length; ii++) {
                    DFSTransactionType.DFSBlockType bt = new DFSTransactionType.DFSBlockType();
                    bt.blockId(ubop.blocks[ii].getBlockId());
                    bt.size(ubop.blocks[ii].getNumBytes());
                    bt.generationStamp(ubop.blocks[ii].getGenerationStamp());

                    upd.blocks().add(bt);
                }
            }

            batch.transactions().add(upd);
        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.UpdateBlocksOp.class, op.getClass()));
        }
    }

    private void handleOpAppend(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.AppendOp) {
            FSEditLogOp.AppendOp aop = (FSEditLogOp.AppendOp) op;
            DFSTransactionType.DFSAppendFileType aft = new DFSTransactionType.DFSAppendFileType();

            aft.id(aop.txid)
                    .op(DFSTransaction.Operation.APPEND);
            aft.file(new DFSTransactionType.DFSFileType().path(aop.path));
            aft.newBlock(aop.newBlock);

            batch.transactions().add(aft);
        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.AppendOp.class, op.getClass()));
        }
    }

    private void handleOpAddBlock(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.AddBlockOp) {
            FSEditLogOp.AddBlockOp abop = (FSEditLogOp.AddBlockOp) op;
            DFSTransactionType.DFSAddBlockType abt = new DFSTransactionType.DFSAddBlockType();

            abt.id(abop.txid)
                    .op(DFSTransaction.Operation.ADD_BLOCK);
            abt.file(new DFSTransactionType.DFSFileType().path(abop.getPath()));

            if (abop.getPenultimateBlock() != null) {
                Block pb = abop.getPenultimateBlock();
                DFSTransactionType.DFSBlockType bt = new DFSTransactionType.DFSBlockType();
                bt.blockId(pb.getBlockId());
                bt.size(pb.getNumBytes());
                bt.generationStamp(pb.getGenerationStamp());

                abt.penultimateBlock(bt);
            }
            if (abop.getLastBlock() != null) {
                Block lb = abop.getLastBlock();
                DFSTransactionType.DFSBlockType bt = new DFSTransactionType.DFSBlockType();
                bt.blockId(lb.getBlockId());
                bt.size(lb.getNumBytes());
                bt.generationStamp(lb.getGenerationStamp());

                abt.lastBlock(bt);
            }

            batch.transactions().add(abt);
        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.AddBlockOp.class, op.getClass()));
        }
    }

    /**
     * Operation notifies creation of new file Inode.
     *
     * @param op    - Add Operation
     * @param batch - Input Edit Log Batch
     * @throws DFSAgentError
     */
    private void handleOpAdd(FSEditLogOp op, DFSEditLogBatch batch) throws DFSAgentError {
        if (op instanceof FSEditLogOp.AddOp) {
            FSEditLogOp.AddOp aop = (FSEditLogOp.AddOp) op;
            DFSTransactionType.DFSAddFileType aft = new DFSTransactionType.DFSAddFileType();

            aft.id(aop.txid)
                    .op(DFSTransaction.Operation.APPEND);
            aft.file(new DFSTransactionType.DFSFileType().path(aop.path).inodeId(aop.inodeId));
            aft.length(aop.length)
                    .blockSize(aop.blockSize)
                    .modifiedTime(aop.mtime)
                    .accessedTime(aop.atime)
                    .overwrite(aop.overwrite);

            if (aop.blocks != null && aop.blocks.length > 0) {
                for (int ii = 0; ii < aop.blocks.length; ii++) {
                    DFSTransactionType.DFSBlockType bt = new DFSTransactionType.DFSBlockType();
                    bt.blockId(aop.blocks[ii].getBlockId());
                    bt.size(aop.blocks[ii].getNumBytes());
                    bt.generationStamp(aop.blocks[ii].getGenerationStamp());

                    aft.blocks().add(bt);
                }
            }
            batch.transactions().add(aft);

        } else {
            throw new DFSAgentError(String.format("Invalid Edit Operation. [expected=%s][actual=%s]", FSEditLogOp.AddOp.class, op.getClass()));
        }
    }
}