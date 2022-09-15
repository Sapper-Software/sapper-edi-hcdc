// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSChangeDeltaOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSChangeDelta)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string txId = 2;</code>
   * @return Whether the txId field is set.
   */
  boolean hasTxId();
  /**
   * <code>required string txId = 2;</code>
   * @return The txId.
   */
  java.lang.String getTxId();
  /**
   * <code>required string txId = 2;</code>
   * @return The bytes for txId.
   */
  com.google.protobuf.ByteString
      getTxIdBytes();

  /**
   * <code>required int64 sequence = 3;</code>
   * @return Whether the sequence field is set.
   */
  boolean hasSequence();
  /**
   * <code>required int64 sequence = 3;</code>
   * @return The sequence.
   */
  long getSequence();

  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSSchemaEntity entity = 4;</code>
   * @return Whether the entity field is set.
   */
  boolean hasEntity();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSSchemaEntity entity = 4;</code>
   * @return The entity.
   */
  ai.sapper.hcdc.common.model.DFSSchemaEntity getEntity();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSSchemaEntity entity = 4;</code>
   */
  ai.sapper.hcdc.common.model.DFSSchemaEntityOrBuilder getEntityOrBuilder();

  /**
   * <code>required string type = 5;</code>
   * @return Whether the type field is set.
   */
  boolean hasType();
  /**
   * <code>required string type = 5;</code>
   * @return The type.
   */
  java.lang.String getType();
  /**
   * <code>required string type = 5;</code>
   * @return The bytes for type.
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>required uint64 timestamp = 6;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <code>required uint64 timestamp = 6;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSSchemaChange schemaChange = 8;</code>
   * @return Whether the schemaChange field is set.
   */
  boolean hasSchemaChange();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSSchemaChange schemaChange = 8;</code>
   * @return The schemaChange.
   */
  ai.sapper.hcdc.common.model.DFSSchemaChange getSchemaChange();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSSchemaChange schemaChange = 8;</code>
   */
  ai.sapper.hcdc.common.model.DFSSchemaChangeOrBuilder getSchemaChangeOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSChangeData dataChange = 9;</code>
   * @return Whether the dataChange field is set.
   */
  boolean hasDataChange();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSChangeData dataChange = 9;</code>
   * @return The dataChange.
   */
  ai.sapper.hcdc.common.model.DFSChangeData getDataChange();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSChangeData dataChange = 9;</code>
   */
  ai.sapper.hcdc.common.model.DFSChangeDataOrBuilder getDataChangeOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAdd fileAdd = 10;</code>
   * @return Whether the fileAdd field is set.
   */
  boolean hasFileAdd();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAdd fileAdd = 10;</code>
   * @return The fileAdd.
   */
  ai.sapper.hcdc.common.model.DFSFileAdd getFileAdd();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAdd fileAdd = 10;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileAddOrBuilder getFileAddOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAppend fileAppend = 11;</code>
   * @return Whether the fileAppend field is set.
   */
  boolean hasFileAppend();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAppend fileAppend = 11;</code>
   * @return The fileAppend.
   */
  ai.sapper.hcdc.common.model.DFSFileAppend getFileAppend();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileAppend fileAppend = 11;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileAppendOrBuilder getFileAppendOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileClose fileClose = 12;</code>
   * @return Whether the fileClose field is set.
   */
  boolean hasFileClose();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileClose fileClose = 12;</code>
   * @return The fileClose.
   */
  ai.sapper.hcdc.common.model.DFSFileClose getFileClose();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileClose fileClose = 12;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileCloseOrBuilder getFileCloseOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileDelete fileDelete = 13;</code>
   * @return Whether the fileDelete field is set.
   */
  boolean hasFileDelete();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileDelete fileDelete = 13;</code>
   * @return The fileDelete.
   */
  ai.sapper.hcdc.common.model.DFSFileDelete getFileDelete();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileDelete fileDelete = 13;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileDeleteOrBuilder getFileDeleteOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileRename fileRename = 14;</code>
   * @return Whether the fileRename field is set.
   */
  boolean hasFileRename();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileRename fileRename = 14;</code>
   * @return The fileRename.
   */
  ai.sapper.hcdc.common.model.DFSFileRename getFileRename();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSFileRename fileRename = 14;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileRenameOrBuilder getFileRenameOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockAdd blockAdd = 15;</code>
   * @return Whether the blockAdd field is set.
   */
  boolean hasBlockAdd();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockAdd blockAdd = 15;</code>
   * @return The blockAdd.
   */
  ai.sapper.hcdc.common.model.DFSBlockAdd getBlockAdd();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockAdd blockAdd = 15;</code>
   */
  ai.sapper.hcdc.common.model.DFSBlockAddOrBuilder getBlockAddOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockUpdate blockUpdate = 16;</code>
   * @return Whether the blockUpdate field is set.
   */
  boolean hasBlockUpdate();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockUpdate blockUpdate = 16;</code>
   * @return The blockUpdate.
   */
  ai.sapper.hcdc.common.model.DFSBlockUpdate getBlockUpdate();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockUpdate blockUpdate = 16;</code>
   */
  ai.sapper.hcdc.common.model.DFSBlockUpdateOrBuilder getBlockUpdateOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockTruncate blockTruncate = 17;</code>
   * @return Whether the blockTruncate field is set.
   */
  boolean hasBlockTruncate();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockTruncate blockTruncate = 17;</code>
   * @return The blockTruncate.
   */
  ai.sapper.hcdc.common.model.DFSBlockTruncate getBlockTruncate();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSBlockTruncate blockTruncate = 17;</code>
   */
  ai.sapper.hcdc.common.model.DFSBlockTruncateOrBuilder getBlockTruncateOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSIgnoreTx ignore = 18;</code>
   * @return Whether the ignore field is set.
   */
  boolean hasIgnore();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSIgnoreTx ignore = 18;</code>
   * @return The ignore.
   */
  ai.sapper.hcdc.common.model.DFSIgnoreTx getIgnore();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSIgnoreTx ignore = 18;</code>
   */
  ai.sapper.hcdc.common.model.DFSIgnoreTxOrBuilder getIgnoreOrBuilder();

  /**
   * <code>.ai_sapper_hcdc_common_model.DFSError error = 19;</code>
   * @return Whether the error field is set.
   */
  boolean hasError();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSError error = 19;</code>
   * @return The error.
   */
  ai.sapper.hcdc.common.model.DFSError getError();
  /**
   * <code>.ai_sapper_hcdc_common_model.DFSError error = 19;</code>
   */
  ai.sapper.hcdc.common.model.DFSErrorOrBuilder getErrorOrBuilder();

  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSSchemaEntity target = 7;</code>
   * @return Whether the target field is set.
   */
  boolean hasTarget();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSSchemaEntity target = 7;</code>
   * @return The target.
   */
  ai.sapper.hcdc.common.model.DFSSchemaEntity getTarget();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSSchemaEntity target = 7;</code>
   */
  ai.sapper.hcdc.common.model.DFSSchemaEntityOrBuilder getTargetOrBuilder();

  public ai.sapper.hcdc.common.model.DFSChangeDelta.ChangeCase getChangeCase();
}