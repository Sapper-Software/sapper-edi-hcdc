// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSDeleteFileOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSDeleteFile)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  boolean hasTransaction();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  ai.sapper.hcdc.common.model.DFSTransaction getTransaction();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  ai.sapper.hcdc.common.model.DFSTransactionOrBuilder getTransactionOrBuilder();

  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  boolean hasFile();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  ai.sapper.hcdc.common.model.DFSFile getFile();
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileOrBuilder getFileOrBuilder();

  /**
   * <code>optional uint64 timestamp = 3;</code>
   */
  long getTimestamp();
}
