// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSTransactionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSTransaction)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 id = 1;</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <code>required int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>required int64 sequence = 2;</code>
   * @return Whether the sequence field is set.
   */
  boolean hasSequence();
  /**
   * <code>required int64 sequence = 2;</code>
   * @return The sequence.
   */
  long getSequence();

  /**
   * <code>required int64 recordId = 4;</code>
   * @return Whether the recordId field is set.
   */
  boolean hasRecordId();
  /**
   * <code>required int64 recordId = 4;</code>
   * @return The recordId.
   */
  long getRecordId();

  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction.Operation op = 5;</code>
   * @return Whether the op field is set.
   */
  boolean hasOp();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction.Operation op = 5;</code>
   * @return The op.
   */
  ai.sapper.hcdc.common.model.DFSTransaction.Operation getOp();

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
}
