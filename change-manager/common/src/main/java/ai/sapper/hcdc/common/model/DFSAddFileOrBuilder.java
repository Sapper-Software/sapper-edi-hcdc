// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSAddFileOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSAddFile)
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
   * <code>optional uint64 length = 3;</code>
   */
  long getLength();

  /**
   * <code>optional uint64 blockSize = 4;</code>
   */
  long getBlockSize();

  /**
   * <code>optional uint64 modifiedTime = 5;</code>
   */
  long getModifiedTime();

  /**
   * <code>optional uint64 accessedTime = 6;</code>
   */
  long getAccessedTime();

  /**
   * <code>repeated .ai_sapper_hcdc_common_model.DFSBlock blocks = 7;</code>
   */
  java.util.List<ai.sapper.hcdc.common.model.DFSBlock> 
      getBlocksList();
  /**
   * <code>repeated .ai_sapper_hcdc_common_model.DFSBlock blocks = 7;</code>
   */
  ai.sapper.hcdc.common.model.DFSBlock getBlocks(int index);
  /**
   * <code>repeated .ai_sapper_hcdc_common_model.DFSBlock blocks = 7;</code>
   */
  int getBlocksCount();
  /**
   * <code>repeated .ai_sapper_hcdc_common_model.DFSBlock blocks = 7;</code>
   */
  java.util.List<? extends ai.sapper.hcdc.common.model.DFSBlockOrBuilder> 
      getBlocksOrBuilderList();
  /**
   * <code>repeated .ai_sapper_hcdc_common_model.DFSBlock blocks = 7;</code>
   */
  ai.sapper.hcdc.common.model.DFSBlockOrBuilder getBlocksOrBuilder(
      int index);

  /**
   * <code>optional bool overwrite = 8;</code>
   */
  boolean getOverwrite();
}