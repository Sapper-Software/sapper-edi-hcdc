package ai.sapper.hcdc.agents.common;

import ai.sapper.hcdc.core.model.EFileType;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;

public interface FormatConverter {
    boolean canParse(@NonNull String path, EFileType fileType) throws IOException;

    File convert(@NonNull File source, @NonNull File output) throws IOException;

    boolean supportsPartial();

    boolean detect(byte[] data, int length) throws IOException;

    EFileType fileType();
}
