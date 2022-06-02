// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

/**
 * Protobuf type {@code ai_sapper_hcdc_common_model.DFSAppendFile}
 */
public  final class DFSAppendFile extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ai_sapper_hcdc_common_model.DFSAppendFile)
    DFSAppendFileOrBuilder {
  // Use DFSAppendFile.newBuilder() to construct.
  private DFSAppendFile(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DFSAppendFile() {
    newBlock_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private DFSAppendFile(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            ai.sapper.hcdc.common.model.DFSTransaction.Builder subBuilder = null;
            if (transaction_ != null) {
              subBuilder = transaction_.toBuilder();
            }
            transaction_ = input.readMessage(ai.sapper.hcdc.common.model.DFSTransaction.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(transaction_);
              transaction_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            ai.sapper.hcdc.common.model.DFSFile.Builder subBuilder = null;
            if (file_ != null) {
              subBuilder = file_.toBuilder();
            }
            file_ = input.readMessage(ai.sapper.hcdc.common.model.DFSFile.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(file_);
              file_ = subBuilder.buildPartial();
            }

            break;
          }
          case 24: {

            newBlock_ = input.readBool();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ai.sapper.hcdc.common.model.HcdcDFSBlockProtos.internal_static_ai_sapper_hcdc_common_model_DFSAppendFile_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ai.sapper.hcdc.common.model.HcdcDFSBlockProtos.internal_static_ai_sapper_hcdc_common_model_DFSAppendFile_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ai.sapper.hcdc.common.model.DFSAppendFile.class, ai.sapper.hcdc.common.model.DFSAppendFile.Builder.class);
  }

  public static final int TRANSACTION_FIELD_NUMBER = 1;
  private ai.sapper.hcdc.common.model.DFSTransaction transaction_;
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  public boolean hasTransaction() {
    return transaction_ != null;
  }
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  public ai.sapper.hcdc.common.model.DFSTransaction getTransaction() {
    return transaction_ == null ? ai.sapper.hcdc.common.model.DFSTransaction.getDefaultInstance() : transaction_;
  }
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  public ai.sapper.hcdc.common.model.DFSTransactionOrBuilder getTransactionOrBuilder() {
    return getTransaction();
  }

  public static final int FILE_FIELD_NUMBER = 2;
  private ai.sapper.hcdc.common.model.DFSFile file_;
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  public boolean hasFile() {
    return file_ != null;
  }
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  public ai.sapper.hcdc.common.model.DFSFile getFile() {
    return file_ == null ? ai.sapper.hcdc.common.model.DFSFile.getDefaultInstance() : file_;
  }
  /**
   * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  public ai.sapper.hcdc.common.model.DFSFileOrBuilder getFileOrBuilder() {
    return getFile();
  }

  public static final int NEWBLOCK_FIELD_NUMBER = 3;
  private boolean newBlock_;
  /**
   * <code>optional bool newBlock = 3;</code>
   */
  public boolean getNewBlock() {
    return newBlock_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (transaction_ != null) {
      output.writeMessage(1, getTransaction());
    }
    if (file_ != null) {
      output.writeMessage(2, getFile());
    }
    if (newBlock_ != false) {
      output.writeBool(3, newBlock_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (transaction_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getTransaction());
    }
    if (file_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getFile());
    }
    if (newBlock_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, newBlock_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof ai.sapper.hcdc.common.model.DFSAppendFile)) {
      return super.equals(obj);
    }
    ai.sapper.hcdc.common.model.DFSAppendFile other = (ai.sapper.hcdc.common.model.DFSAppendFile) obj;

    boolean result = true;
    result = result && (hasTransaction() == other.hasTransaction());
    if (hasTransaction()) {
      result = result && getTransaction()
          .equals(other.getTransaction());
    }
    result = result && (hasFile() == other.hasFile());
    if (hasFile()) {
      result = result && getFile()
          .equals(other.getFile());
    }
    result = result && (getNewBlock()
        == other.getNewBlock());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (hasTransaction()) {
      hash = (37 * hash) + TRANSACTION_FIELD_NUMBER;
      hash = (53 * hash) + getTransaction().hashCode();
    }
    if (hasFile()) {
      hash = (37 * hash) + FILE_FIELD_NUMBER;
      hash = (53 * hash) + getFile().hashCode();
    }
    hash = (37 * hash) + NEWBLOCK_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getNewBlock());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ai.sapper.hcdc.common.model.DFSAppendFile parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(ai.sapper.hcdc.common.model.DFSAppendFile prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ai_sapper_hcdc_common_model.DFSAppendFile}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ai_sapper_hcdc_common_model.DFSAppendFile)
      ai.sapper.hcdc.common.model.DFSAppendFileOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ai.sapper.hcdc.common.model.HcdcDFSBlockProtos.internal_static_ai_sapper_hcdc_common_model_DFSAppendFile_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ai.sapper.hcdc.common.model.HcdcDFSBlockProtos.internal_static_ai_sapper_hcdc_common_model_DFSAppendFile_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ai.sapper.hcdc.common.model.DFSAppendFile.class, ai.sapper.hcdc.common.model.DFSAppendFile.Builder.class);
    }

    // Construct using ai.sapper.hcdc.common.model.DFSAppendFile.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (transactionBuilder_ == null) {
        transaction_ = null;
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }
      if (fileBuilder_ == null) {
        file_ = null;
      } else {
        file_ = null;
        fileBuilder_ = null;
      }
      newBlock_ = false;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ai.sapper.hcdc.common.model.HcdcDFSBlockProtos.internal_static_ai_sapper_hcdc_common_model_DFSAppendFile_descriptor;
    }

    public ai.sapper.hcdc.common.model.DFSAppendFile getDefaultInstanceForType() {
      return ai.sapper.hcdc.common.model.DFSAppendFile.getDefaultInstance();
    }

    public ai.sapper.hcdc.common.model.DFSAppendFile build() {
      ai.sapper.hcdc.common.model.DFSAppendFile result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public ai.sapper.hcdc.common.model.DFSAppendFile buildPartial() {
      ai.sapper.hcdc.common.model.DFSAppendFile result = new ai.sapper.hcdc.common.model.DFSAppendFile(this);
      if (transactionBuilder_ == null) {
        result.transaction_ = transaction_;
      } else {
        result.transaction_ = transactionBuilder_.build();
      }
      if (fileBuilder_ == null) {
        result.file_ = file_;
      } else {
        result.file_ = fileBuilder_.build();
      }
      result.newBlock_ = newBlock_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ai.sapper.hcdc.common.model.DFSAppendFile) {
        return mergeFrom((ai.sapper.hcdc.common.model.DFSAppendFile)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ai.sapper.hcdc.common.model.DFSAppendFile other) {
      if (other == ai.sapper.hcdc.common.model.DFSAppendFile.getDefaultInstance()) return this;
      if (other.hasTransaction()) {
        mergeTransaction(other.getTransaction());
      }
      if (other.hasFile()) {
        mergeFile(other.getFile());
      }
      if (other.getNewBlock() != false) {
        setNewBlock(other.getNewBlock());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      ai.sapper.hcdc.common.model.DFSAppendFile parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ai.sapper.hcdc.common.model.DFSAppendFile) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private ai.sapper.hcdc.common.model.DFSTransaction transaction_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        ai.sapper.hcdc.common.model.DFSTransaction, ai.sapper.hcdc.common.model.DFSTransaction.Builder, ai.sapper.hcdc.common.model.DFSTransactionOrBuilder> transactionBuilder_;
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public boolean hasTransaction() {
      return transactionBuilder_ != null || transaction_ != null;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public ai.sapper.hcdc.common.model.DFSTransaction getTransaction() {
      if (transactionBuilder_ == null) {
        return transaction_ == null ? ai.sapper.hcdc.common.model.DFSTransaction.getDefaultInstance() : transaction_;
      } else {
        return transactionBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public Builder setTransaction(ai.sapper.hcdc.common.model.DFSTransaction value) {
      if (transactionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        transaction_ = value;
        onChanged();
      } else {
        transactionBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public Builder setTransaction(
        ai.sapper.hcdc.common.model.DFSTransaction.Builder builderForValue) {
      if (transactionBuilder_ == null) {
        transaction_ = builderForValue.build();
        onChanged();
      } else {
        transactionBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public Builder mergeTransaction(ai.sapper.hcdc.common.model.DFSTransaction value) {
      if (transactionBuilder_ == null) {
        if (transaction_ != null) {
          transaction_ =
            ai.sapper.hcdc.common.model.DFSTransaction.newBuilder(transaction_).mergeFrom(value).buildPartial();
        } else {
          transaction_ = value;
        }
        onChanged();
      } else {
        transactionBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public Builder clearTransaction() {
      if (transactionBuilder_ == null) {
        transaction_ = null;
        onChanged();
      } else {
        transaction_ = null;
        transactionBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public ai.sapper.hcdc.common.model.DFSTransaction.Builder getTransactionBuilder() {
      
      onChanged();
      return getTransactionFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    public ai.sapper.hcdc.common.model.DFSTransactionOrBuilder getTransactionOrBuilder() {
      if (transactionBuilder_ != null) {
        return transactionBuilder_.getMessageOrBuilder();
      } else {
        return transaction_ == null ?
            ai.sapper.hcdc.common.model.DFSTransaction.getDefaultInstance() : transaction_;
      }
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        ai.sapper.hcdc.common.model.DFSTransaction, ai.sapper.hcdc.common.model.DFSTransaction.Builder, ai.sapper.hcdc.common.model.DFSTransactionOrBuilder> 
        getTransactionFieldBuilder() {
      if (transactionBuilder_ == null) {
        transactionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            ai.sapper.hcdc.common.model.DFSTransaction, ai.sapper.hcdc.common.model.DFSTransaction.Builder, ai.sapper.hcdc.common.model.DFSTransactionOrBuilder>(
                getTransaction(),
                getParentForChildren(),
                isClean());
        transaction_ = null;
      }
      return transactionBuilder_;
    }

    private ai.sapper.hcdc.common.model.DFSFile file_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        ai.sapper.hcdc.common.model.DFSFile, ai.sapper.hcdc.common.model.DFSFile.Builder, ai.sapper.hcdc.common.model.DFSFileOrBuilder> fileBuilder_;
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public boolean hasFile() {
      return fileBuilder_ != null || file_ != null;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public ai.sapper.hcdc.common.model.DFSFile getFile() {
      if (fileBuilder_ == null) {
        return file_ == null ? ai.sapper.hcdc.common.model.DFSFile.getDefaultInstance() : file_;
      } else {
        return fileBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public Builder setFile(ai.sapper.hcdc.common.model.DFSFile value) {
      if (fileBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        file_ = value;
        onChanged();
      } else {
        fileBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public Builder setFile(
        ai.sapper.hcdc.common.model.DFSFile.Builder builderForValue) {
      if (fileBuilder_ == null) {
        file_ = builderForValue.build();
        onChanged();
      } else {
        fileBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public Builder mergeFile(ai.sapper.hcdc.common.model.DFSFile value) {
      if (fileBuilder_ == null) {
        if (file_ != null) {
          file_ =
            ai.sapper.hcdc.common.model.DFSFile.newBuilder(file_).mergeFrom(value).buildPartial();
        } else {
          file_ = value;
        }
        onChanged();
      } else {
        fileBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public Builder clearFile() {
      if (fileBuilder_ == null) {
        file_ = null;
        onChanged();
      } else {
        file_ = null;
        fileBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public ai.sapper.hcdc.common.model.DFSFile.Builder getFileBuilder() {
      
      onChanged();
      return getFileFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    public ai.sapper.hcdc.common.model.DFSFileOrBuilder getFileOrBuilder() {
      if (fileBuilder_ != null) {
        return fileBuilder_.getMessageOrBuilder();
      } else {
        return file_ == null ?
            ai.sapper.hcdc.common.model.DFSFile.getDefaultInstance() : file_;
      }
    }
    /**
     * <code>optional .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        ai.sapper.hcdc.common.model.DFSFile, ai.sapper.hcdc.common.model.DFSFile.Builder, ai.sapper.hcdc.common.model.DFSFileOrBuilder> 
        getFileFieldBuilder() {
      if (fileBuilder_ == null) {
        fileBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            ai.sapper.hcdc.common.model.DFSFile, ai.sapper.hcdc.common.model.DFSFile.Builder, ai.sapper.hcdc.common.model.DFSFileOrBuilder>(
                getFile(),
                getParentForChildren(),
                isClean());
        file_ = null;
      }
      return fileBuilder_;
    }

    private boolean newBlock_ ;
    /**
     * <code>optional bool newBlock = 3;</code>
     */
    public boolean getNewBlock() {
      return newBlock_;
    }
    /**
     * <code>optional bool newBlock = 3;</code>
     */
    public Builder setNewBlock(boolean value) {
      
      newBlock_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool newBlock = 3;</code>
     */
    public Builder clearNewBlock() {
      
      newBlock_ = false;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:ai_sapper_hcdc_common_model.DFSAppendFile)
  }

  // @@protoc_insertion_point(class_scope:ai_sapper_hcdc_common_model.DFSAppendFile)
  private static final ai.sapper.hcdc.common.model.DFSAppendFile DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ai.sapper.hcdc.common.model.DFSAppendFile();
  }

  public static ai.sapper.hcdc.common.model.DFSAppendFile getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DFSAppendFile>
      PARSER = new com.google.protobuf.AbstractParser<DFSAppendFile>() {
    public DFSAppendFile parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new DFSAppendFile(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DFSAppendFile> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DFSAppendFile> getParserForType() {
    return PARSER;
  }

  public ai.sapper.hcdc.common.model.DFSAppendFile getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

