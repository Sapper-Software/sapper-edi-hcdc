// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSChangeDeltaOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ai_sapper_hcdc_common_model.DFSChangeDelta)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string namespace = 1;</code>
   */
  java.lang.String getNamespace();
  /**
   * <code>optional string namespace = 1;</code>
   */
  com.google.protobuf.ByteString
      getNamespaceBytes();

  /**
   * <code>optional string txId = 2;</code>
   */
  java.lang.String getTxId();
  /**
   * <code>optional string txId = 2;</code>
   */
  com.google.protobuf.ByteString
      getTxIdBytes();

  /**
   * <code>optional string entity = 3;</code>
   */
  java.lang.String getEntity();
  /**
   * <code>optional string entity = 3;</code>
   */
  com.google.protobuf.ByteString
      getEntityBytes();

  /**
   * <code>optional string type = 4;</code>
   */
  java.lang.String getType();
  /**
   * <code>optional string type = 4;</code>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>optional uint64 timestamp = 5;</code>
   */
  long getTimestamp();

  /**
   * <code>optional bytes body = 6;</code>
   */
  com.google.protobuf.ByteString getBody();
}
