package ai.sapper.hcdc.agents.namenode.model;

import ai.sapper.hcdc.agents.namenode.DFSAgentError;
import ai.sapper.hcdc.agents.namenode.NameNodeEnv;
import ai.sapper.hcdc.common.messaging.ChangeDeltaData;
import ai.sapper.hcdc.common.messaging.ChangeDeltaMessage;
import ai.sapper.hcdc.common.model.*;
import com.google.common.base.Preconditions;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
public abstract class DFSTransactionType<T> implements Comparable<DFSTransactionType<T>> {
    private long id;
    private DFSTransaction.Operation op;
    private long timestamp;

    public DFSTransactionType() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NonNull DFSTransactionType<T> o) {
        return (int) (id - o.id);
    }

    public DFSTransaction getTransactionProto() {
        return DFSTransaction.newBuilder().setTransactionId(id).setOp(op).setTimestamp(timestamp).build();
    }

    public void parseFrom(@NonNull DFSTransaction transaction) {
        id = transaction.getTransactionId();
        op = transaction.getOp();
        timestamp = transaction.getTimestamp();
    }

    public abstract T convertToProto() throws DFSAgentError;

    public abstract void parseFrom(byte[] data) throws DFSAgentError;

    public abstract void parseFrom(T proto) throws DFSAgentError;

    public abstract ChangeDeltaMessage getMessage() throws DFSAgentError;


    /**
     * @param message
     * @throws DFSAgentError
     */
    public void parseFrom(@NonNull ChangeDeltaMessage message) throws DFSAgentError {
        Preconditions.checkNotNull(message.getData());
        Preconditions.checkArgument(getClass().getCanonicalName().compareTo(message.getData().getType()) == 0);

        parseFrom(message.getData().getData());
        long txId = Long.parseLong(message.getData().getTxId());
        if (txId != this.id()) {
            throw new DFSAgentError(String.format("Transaction ID mismatch. [expected=%d][actual=%d]", id(), txId));
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSFileType {
        private String path;
        private long inodeId = Long.MIN_VALUE;

        public DFSFile getProto() {
            return DFSFile.newBuilder().setPath(path).setInodeId(inodeId).build();
        }

        public void parse(@NonNull DFSFile file) {
            this.path = file.getPath();
            this.inodeId = file.getInodeId();
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSBlockType {
        private long blockId;
        private long size;
        private long generationStamp;

        public DFSBlock getProto() {
            return DFSBlock.newBuilder().setBlockId(blockId).setSize(size).setGenerationStamp(generationStamp).build();
        }

        public void parse(@NonNull DFSBlock block) {
            this.blockId = block.getBlockId();
            this.size = block.getSize();
            this.generationStamp = block.getGenerationStamp();
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSAddBlockType extends DFSTransactionType<DFSAddBlock> {
        private DFSFileType file;
        private DFSBlockType penultimateBlock;
        private DFSBlockType lastBlock;

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSAddBlock convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);

            DFSAddBlock.Builder builder = DFSAddBlock.newBuilder();
            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            if (penultimateBlock != null) {
                builder.setPenultimateBlock(penultimateBlock.getProto());
            }
            if (lastBlock != null) {
                builder.setLastBlock(lastBlock.getProto());
            }
            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSAddBlock addBlock = DFSAddBlock.parseFrom(data);
                parseFrom(addBlock);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSAddBlock proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            if (proto.hasPenultimateBlock()) {
                penultimateBlock = new DFSBlockType();
                penultimateBlock.parse(proto.getPenultimateBlock());
            }
            if (proto.hasLastBlock()) {
                lastBlock = new DFSBlockType();
                lastBlock.parse(proto.getLastBlock());
            }
        }

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSAddBlock proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }

    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSAddFileType extends DFSTransactionType<DFSAddFile> {
        private DFSFileType file;
        private long length;
        private long blockSize;
        private long modifiedTime;
        private long accessedTime;
        private boolean overwrite;
        private final List<DFSBlockType> blocks = new ArrayList<>();

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSAddFile convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);

            DFSAddFile.Builder builder = DFSAddFile.newBuilder();
            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            builder.setLength(length).setBlockSize(blockSize).setModifiedTime(modifiedTime).setAccessedTime(accessedTime).setOverwrite(overwrite);
            if (!blocks.isEmpty()) {
                for (DFSBlockType block : blocks) {
                    builder.addBlocks(block.getProto());
                }
            }
            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSAddFile addFile = DFSAddFile.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSAddFile proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            this.length = proto.getLength();
            this.blockSize = proto.getBlockSize();
            this.modifiedTime = proto.getModifiedTime();
            this.accessedTime = proto.getAccessedTime();
            this.overwrite = proto.getOverwrite();

            List<DFSBlock> bl = proto.getBlocksList();
            if (bl != null && !bl.isEmpty()) {
                for (DFSBlock block : bl) {
                    DFSBlockType bt = new DFSBlockType();
                    bt.parse(block);
                    blocks.add(bt);
                }
            }
        }

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSAddFile proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSAppendFileType extends DFSTransactionType<DFSAppendFile> {
        private DFSFileType file;
        private boolean newBlock;

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSAppendFile convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);

            DFSAppendFile.Builder builder = DFSAppendFile.newBuilder();
            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            builder.setNewBlock(newBlock);

            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSAppendFile addFile = DFSAppendFile.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSAppendFile proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            this.newBlock = proto.getNewBlock();
        }

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSAppendFile proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static final class DFSCloseFileType extends DFSTransactionType<DFSCloseFile> {
        private DFSFileType file;
        private long length;
        private long blockSize;
        private long modifiedTime;
        private long accessedTime;
        private boolean overwrite;
        private final List<DFSBlockType> blocks = new ArrayList<>();

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSCloseFile convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);

            DFSCloseFile.Builder builder = DFSCloseFile.newBuilder();

            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            builder.setLength(length).setBlockSize(blockSize).setModifiedTime(modifiedTime).setAccessedTime(accessedTime).setOverwrite(overwrite);
            if (!blocks.isEmpty()) {
                for (DFSBlockType block : blocks) {
                    builder.addBlocks(block.getProto());
                }
            }
            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSCloseFile addFile = DFSCloseFile.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSCloseFile proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            this.length = proto.getLength();
            this.blockSize = proto.getBlockSize();
            this.modifiedTime = proto.getModifiedTime();
            this.accessedTime = proto.getAccessedTime();
            this.overwrite = proto.getOverwrite();

            List<DFSBlock> bl = proto.getBlocksList();
            if (bl != null && !bl.isEmpty()) {
                for (DFSBlock block : bl) {
                    DFSBlockType bt = new DFSBlockType();
                    bt.parse(block);
                    blocks.add(bt);
                }
            }
        }


        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSCloseFile proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSDeleteFileType extends DFSTransactionType<DFSDeleteFile> {
        private DFSFileType file;

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSDeleteFile convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);

            DFSDeleteFile.Builder builder = DFSDeleteFile.newBuilder();
            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            builder.setTimestamp(timestamp());

            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSDeleteFile addFile = DFSDeleteFile.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSDeleteFile proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            timestamp(proto.getTimestamp());
        }


        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSDeleteFile proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSTruncateBlockType extends DFSTransactionType<DFSTruncateBlock> {
        private DFSFileType file;
        private DFSBlockType block;
        private long newLength;

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSTruncateBlock convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);
            Preconditions.checkNotNull(block);

            DFSTruncateBlock.Builder builder = DFSTruncateBlock.newBuilder();
            builder.setTransaction(getTransactionProto())
                    .setFile(file.getProto())
                    .setBlock(block.getProto())
                    .setNewLength(newLength);

            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSTruncateBlock addFile = DFSTruncateBlock.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSTruncateBlock proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            this.newLength = proto.getNewLength();

            file = new DFSFileType();
            file.parse(proto.getFile());

            block = new DFSBlockType();
            block.parse(proto.getBlock());
        }


        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSTruncateBlock proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static class DFSUpdateBlocksType extends DFSTransactionType<DFSUpdateBlocks> {
        private DFSFileType file;
        private final List<DFSBlockType> blocks = new ArrayList<>();

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSUpdateBlocks convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(file);
            Preconditions.checkState(!blocks.isEmpty());

            DFSUpdateBlocks.Builder builder = DFSUpdateBlocks.newBuilder();
            builder.setTransaction(getTransactionProto()).setFile(file.getProto());
            for (DFSBlockType block : blocks) {
                builder.addBlocks(block.getProto());
            }

            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSUpdateBlocks addFile = DFSUpdateBlocks.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSUpdateBlocks proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasFile());

            this.parseFrom(proto.getTransaction());
            file = new DFSFileType();
            file.parse(proto.getFile());

            List<DFSBlock> bl = proto.getBlocksList();
            for (DFSBlock block : bl) {
                DFSBlockType bt = new DFSBlockType();
                bt.parse(block);
                blocks.add(bt);
            }
        }


        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSUpdateBlocks proto = convertToProto();

            message.setKey(proto.getFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    @ToString
    public static final class DFSRenameFileType extends DFSTransactionType<DFSRenameFile> {
        private DFSFileType source;
        private DFSFileType dest;
        private long length;
        private DFSRenameFile.RenameOpts opts = DFSRenameFile.RenameOpts.NONE;

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSRenameFile convertToProto() throws DFSAgentError {
            Preconditions.checkNotNull(source);
            Preconditions.checkNotNull(dest);

            DFSRenameFile.Builder builder = DFSRenameFile.newBuilder();
            builder.setTransaction(getTransactionProto())
                    .setSrcFile(source.getProto())
                    .setDestFile(dest.getProto())
                    .setLength(length)
                    .setOpts(opts);

            return builder.build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSRenameFile addFile = DFSRenameFile.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSRenameFile proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            Preconditions.checkArgument(proto.hasSrcFile());
            Preconditions.checkArgument(proto.hasDestFile());

            this.parseFrom(proto.getTransaction());
            source = new DFSFileType();
            source.parse(proto.getSrcFile());
            dest = new DFSFileType();
            dest.parse(proto.getDestFile());

            length = proto.getLength();
            opts = proto.getOpts();
        }


        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSRenameFile proto = convertToProto();

            message.setKey(proto.getDestFile().getPath());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(proto.getDestFile().getPath());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }

    public static class DFSIgnoreTxType extends DFSTransactionType<DFSIgnoreTx> {

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public DFSIgnoreTx convertToProto() throws DFSAgentError {
            DFSIgnoreTx.Builder builder = DFSIgnoreTx.newBuilder();

            return builder.setTransaction(getTransactionProto()).build();
        }

        /**
         * @param data
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(byte[] data) throws DFSAgentError {
            try {
                DFSIgnoreTx addFile = DFSIgnoreTx.parseFrom(data);
                parseFrom(addFile);
            } catch (InvalidProtocolBufferException e) {
                throw new DFSAgentError(String.format("Error reading from byte array. [type=%s]", getClass().getCanonicalName()), e);
            }
        }

        /**
         * @param proto
         * @throws DFSAgentError
         */
        @Override
        public void parseFrom(DFSIgnoreTx proto) throws DFSAgentError {
            Preconditions.checkArgument(proto.hasTransaction());
            this.parseFrom(proto.getTransaction());
        }

        /**
         * @return
         * @throws DFSAgentError
         */
        @Override
        public ChangeDeltaMessage getMessage() throws DFSAgentError {
            ChangeDeltaMessage message = new ChangeDeltaMessage();
            DFSIgnoreTx proto = convertToProto();

            message.setKey(NameNodeEnv.get().ignoreTnxKey());
            ChangeDeltaData data = new ChangeDeltaData();
            data.setType(proto.getClass().getCanonicalName());
            data.setEntity(NameNodeEnv.get().ignoreTnxKey());
            data.setNamespace(NameNodeEnv.get().namespace());
            data.setTimestamp(System.currentTimeMillis());
            data.setTxId(String.valueOf(proto.getTransaction().getTransactionId()));
            data.setData(proto.toByteArray());
            message.setData(data);

            return message;
        }
    }
}
