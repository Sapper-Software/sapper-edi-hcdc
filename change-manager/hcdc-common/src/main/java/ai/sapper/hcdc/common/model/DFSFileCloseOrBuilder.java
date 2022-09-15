// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSFileCloseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSFileClose)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   * @return Whether the transaction field is set.
   */
  boolean hasTransaction();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   * @return The transaction.
   */
  ai.sapper.hcdc.common.model.DFSTransaction getTransaction();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  ai.sapper.hcdc.common.model.DFSTransactionOrBuilder getTransactionOrBuilder();

  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   * @return Whether the file field is set.
   */
  boolean hasFile();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   * @return The file.
   */
  ai.sapper.hcdc.common.model.DFSFile getFile();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileOrBuilder getFileOrBuilder();

  /**
   * <code>required uint64 length = 3;</code>
   * @return Whether the length field is set.
   */
  boolean hasLength();
  /**
   * <code>required uint64 length = 3;</code>
   * @return The length.
   */
  long getLength();

  /**
   * <code>required uint64 blockSize = 4;</code>
   * @return Whether the blockSize field is set.
   */
  boolean hasBlockSize();
  /**
   * <code>required uint64 blockSize = 4;</code>
   * @return The blockSize.
   */
  long getBlockSize();

  /**
   * <code>required uint64 modifiedTime = 5;</code>
   * @return Whether the modifiedTime field is set.
   */
  boolean hasModifiedTime();
  /**
   * <code>required uint64 modifiedTime = 5;</code>
   * @return The modifiedTime.
   */
  long getModifiedTime();

  /**
   * <code>required uint64 accessedTime = 6;</code>
   * @return Whether the accessedTime field is set.
   */
  boolean hasAccessedTime();
  /**
   * <code>required uint64 accessedTime = 6;</code>
   * @return The accessedTime.
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
   * @return Whether the overwrite field is set.
   */
  boolean hasOverwrite();
  /**
   * <code>optional bool overwrite = 8;</code>
   * @return The overwrite.
   */
  boolean getOverwrite();
}