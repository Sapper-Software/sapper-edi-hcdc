// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

/**
 * Protobuf type {@code ai_sapper_hcdc_common_model.DFSFile}
 */
public  final class DFSFile extends
    com.google.protobuf.GeneratedMessage
    implements DFSFileOrBuilder {
  // Use DFSFile.newBuilder() to construct.
  private DFSFile(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
    this.unknownFields = builder.getUnknownFields();
  }
  private DFSFile(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

  private static final DFSFile defaultInstance;
  public static DFSFile getDefaultInstance() {
    return defaultInstance;
  }

  public DFSFile getDefaultInstanceForType() {
    return defaultInstance;
  }

  private final com.google.protobuf.UnknownFieldSet unknownFields;
  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
    return this.unknownFields;
  }
  private DFSFile(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    initFields();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            bitField0_ |= 0x00000001;
            namespace_ = input.readBytes();
            break;
          }
          case 18: {
            bitField0_ |= 0x00000002;
            path_ = input.readBytes();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            inodeId_ = input.readInt64();
            break;
          }
          case 34: {
            bitField0_ |= 0x00000008;
            fileType_ = input.readBytes();
            break;
          }
          case 42: {
            bitField0_ |= 0x00000010;
            schemaLocation_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e.getMessage()).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ai.sapper.hcdc.common.model.DFSBlockProto.internal_static_ai_sapper_hcdc_common_model_DFSFile_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ai.sapper.hcdc.common.model.DFSBlockProto.internal_static_ai_sapper_hcdc_common_model_DFSFile_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ai.sapper.hcdc.common.model.DFSFile.class, ai.sapper.hcdc.common.model.DFSFile.Builder.class);
  }

  public static com.google.protobuf.Parser<DFSFile> PARSER =
      new com.google.protobuf.AbstractParser<DFSFile>() {
    public DFSFile parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DFSFile(input, extensionRegistry);
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<DFSFile> getParserForType() {
    return PARSER;
  }

  private int bitField0_;
  // required string namespace = 1;
  public static final int NAMESPACE_FIELD_NUMBER = 1;
  private java.lang.Object namespace_;
  /**
   * <code>required string namespace = 1;</code>
   */
  public boolean hasNamespace() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string namespace = 1;</code>
   */
  public java.lang.String getNamespace() {
    java.lang.Object ref = namespace_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        namespace_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string namespace = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNamespaceBytes() {
    java.lang.Object ref = namespace_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      namespace_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  // required string path = 2;
  public static final int PATH_FIELD_NUMBER = 2;
  private java.lang.Object path_;
  /**
   * <code>required string path = 2;</code>
   */
  public boolean hasPath() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string path = 2;</code>
   */
  public java.lang.String getPath() {
    java.lang.Object ref = path_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        path_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string path = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPathBytes() {
    java.lang.Object ref = path_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      path_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  // required int64 inodeId = 3;
  public static final int INODEID_FIELD_NUMBER = 3;
  private long inodeId_;
  /**
   * <code>required int64 inodeId = 3;</code>
   */
  public boolean hasInodeId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int64 inodeId = 3;</code>
   */
  public long getInodeId() {
    return inodeId_;
  }

  // optional string fileType = 4;
  public static final int FILETYPE_FIELD_NUMBER = 4;
  private java.lang.Object fileType_;
  /**
   * <code>optional string fileType = 4;</code>
   */
  public boolean hasFileType() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional string fileType = 4;</code>
   */
  public java.lang.String getFileType() {
    java.lang.Object ref = fileType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        fileType_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string fileType = 4;</code>
   */
  public com.google.protobuf.ByteString
      getFileTypeBytes() {
    java.lang.Object ref = fileType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      fileType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  // optional string schemaLocation = 5;
  public static final int SCHEMALOCATION_FIELD_NUMBER = 5;
  private java.lang.Object schemaLocation_;
  /**
   * <code>optional string schemaLocation = 5;</code>
   */
  public boolean hasSchemaLocation() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional string schemaLocation = 5;</code>
   */
  public java.lang.String getSchemaLocation() {
    java.lang.Object ref = schemaLocation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        schemaLocation_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string schemaLocation = 5;</code>
   */
  public com.google.protobuf.ByteString
      getSchemaLocationBytes() {
    java.lang.Object ref = schemaLocation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      schemaLocation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private void initFields() {
    namespace_ = "";
    path_ = "";
    inodeId_ = 0L;
    fileType_ = "";
    schemaLocation_ = "";
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;

    if (!hasNamespace()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPath()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasInodeId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeBytes(1, getNamespaceBytes());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, getPathBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, inodeId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeBytes(4, getFileTypeBytes());
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeBytes(5, getSchemaLocationBytes());
    }
    getUnknownFields().writeTo(output);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, getNamespaceBytes());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getPathBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, inodeId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, getFileTypeBytes());
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(5, getSchemaLocationBytes());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }

  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static ai.sapper.hcdc.common.model.DFSFile parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(ai.sapper.hcdc.common.model.DFSFile prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ai_sapper_hcdc_common_model.DFSFile}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder>
     implements ai.sapper.hcdc.common.model.DFSFileOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ai.sapper.hcdc.common.model.DFSBlockProto.internal_static_ai_sapper_hcdc_common_model_DFSFile_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ai.sapper.hcdc.common.model.DFSBlockProto.internal_static_ai_sapper_hcdc_common_model_DFSFile_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ai.sapper.hcdc.common.model.DFSFile.class, ai.sapper.hcdc.common.model.DFSFile.Builder.class);
    }

    // Construct using ai.sapper.hcdc.common.model.DFSFile.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    private static Builder create() {
      return new Builder();
    }

    public Builder clear() {
      super.clear();
      namespace_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      path_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      inodeId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      fileType_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      schemaLocation_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ai.sapper.hcdc.common.model.DFSBlockProto.internal_static_ai_sapper_hcdc_common_model_DFSFile_descriptor;
    }

    public ai.sapper.hcdc.common.model.DFSFile getDefaultInstanceForType() {
      return ai.sapper.hcdc.common.model.DFSFile.getDefaultInstance();
    }

    public ai.sapper.hcdc.common.model.DFSFile build() {
      ai.sapper.hcdc.common.model.DFSFile result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ai.sapper.hcdc.common.model.DFSFile buildPartial() {
      ai.sapper.hcdc.common.model.DFSFile result = new ai.sapper.hcdc.common.model.DFSFile(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.namespace_ = namespace_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.path_ = path_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.inodeId_ = inodeId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.fileType_ = fileType_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.schemaLocation_ = schemaLocation_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ai.sapper.hcdc.common.model.DFSFile) {
        return mergeFrom((ai.sapper.hcdc.common.model.DFSFile)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ai.sapper.hcdc.common.model.DFSFile other) {
      if (other == ai.sapper.hcdc.common.model.DFSFile.getDefaultInstance()) return this;
      if (other.hasNamespace()) {
        bitField0_ |= 0x00000001;
        namespace_ = other.namespace_;
        onChanged();
      }
      if (other.hasPath()) {
        bitField0_ |= 0x00000002;
        path_ = other.path_;
        onChanged();
      }
      if (other.hasInodeId()) {
        setInodeId(other.getInodeId());
      }
      if (other.hasFileType()) {
        bitField0_ |= 0x00000008;
        fileType_ = other.fileType_;
        onChanged();
      }
      if (other.hasSchemaLocation()) {
        bitField0_ |= 0x00000010;
        schemaLocation_ = other.schemaLocation_;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }

    public final boolean isInitialized() {
      if (!hasNamespace()) {
        
        return false;
      }
      if (!hasPath()) {
        
        return false;
      }
      if (!hasInodeId()) {
        
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      ai.sapper.hcdc.common.model.DFSFile parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ai.sapper.hcdc.common.model.DFSFile) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    // required string namespace = 1;
    private java.lang.Object namespace_ = "";
    /**
     * <code>required string namespace = 1;</code>
     */
    public boolean hasNamespace() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string namespace = 1;</code>
     */
    public java.lang.String getNamespace() {
      java.lang.Object ref = namespace_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        namespace_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string namespace = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNamespaceBytes() {
      java.lang.Object ref = namespace_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        namespace_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string namespace = 1;</code>
     */
    public Builder setNamespace(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      namespace_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string namespace = 1;</code>
     */
    public Builder clearNamespace() {
      bitField0_ = (bitField0_ & ~0x00000001);
      namespace_ = getDefaultInstance().getNamespace();
      onChanged();
      return this;
    }
    /**
     * <code>required string namespace = 1;</code>
     */
    public Builder setNamespaceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      namespace_ = value;
      onChanged();
      return this;
    }

    // required string path = 2;
    private java.lang.Object path_ = "";
    /**
     * <code>required string path = 2;</code>
     */
    public boolean hasPath() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string path = 2;</code>
     */
    public java.lang.String getPath() {
      java.lang.Object ref = path_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        path_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string path = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPathBytes() {
      java.lang.Object ref = path_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        path_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string path = 2;</code>
     */
    public Builder setPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      path_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string path = 2;</code>
     */
    public Builder clearPath() {
      bitField0_ = (bitField0_ & ~0x00000002);
      path_ = getDefaultInstance().getPath();
      onChanged();
      return this;
    }
    /**
     * <code>required string path = 2;</code>
     */
    public Builder setPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      path_ = value;
      onChanged();
      return this;
    }

    // required int64 inodeId = 3;
    private long inodeId_ ;
    /**
     * <code>required int64 inodeId = 3;</code>
     */
    public boolean hasInodeId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int64 inodeId = 3;</code>
     */
    public long getInodeId() {
      return inodeId_;
    }
    /**
     * <code>required int64 inodeId = 3;</code>
     */
    public Builder setInodeId(long value) {
      bitField0_ |= 0x00000004;
      inodeId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 inodeId = 3;</code>
     */
    public Builder clearInodeId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      inodeId_ = 0L;
      onChanged();
      return this;
    }

    // optional string fileType = 4;
    private java.lang.Object fileType_ = "";
    /**
     * <code>optional string fileType = 4;</code>
     */
    public boolean hasFileType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string fileType = 4;</code>
     */
    public java.lang.String getFileType() {
      java.lang.Object ref = fileType_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        fileType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string fileType = 4;</code>
     */
    public com.google.protobuf.ByteString
        getFileTypeBytes() {
      java.lang.Object ref = fileType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fileType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string fileType = 4;</code>
     */
    public Builder setFileType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      fileType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string fileType = 4;</code>
     */
    public Builder clearFileType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      fileType_ = getDefaultInstance().getFileType();
      onChanged();
      return this;
    }
    /**
     * <code>optional string fileType = 4;</code>
     */
    public Builder setFileTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      fileType_ = value;
      onChanged();
      return this;
    }

    // optional string schemaLocation = 5;
    private java.lang.Object schemaLocation_ = "";
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public boolean hasSchemaLocation() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public java.lang.String getSchemaLocation() {
      java.lang.Object ref = schemaLocation_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        schemaLocation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public com.google.protobuf.ByteString
        getSchemaLocationBytes() {
      java.lang.Object ref = schemaLocation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        schemaLocation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public Builder setSchemaLocation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      schemaLocation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public Builder clearSchemaLocation() {
      bitField0_ = (bitField0_ & ~0x00000010);
      schemaLocation_ = getDefaultInstance().getSchemaLocation();
      onChanged();
      return this;
    }
    /**
     * <code>optional string schemaLocation = 5;</code>
     */
    public Builder setSchemaLocationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      schemaLocation_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:ai_sapper_hcdc_common_model.DFSFile)
  }

  static {
    defaultInstance = new DFSFile(true);
    defaultInstance.initFields();
  }

  // @@protoc_insertion_point(class_scope:ai_sapper_hcdc_common_model.DFSFile)
}

