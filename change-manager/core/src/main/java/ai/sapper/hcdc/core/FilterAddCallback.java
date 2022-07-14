package ai.sapper.hcdc.core;

import lombok.NonNull;

public interface FilterAddCallback {
    void process(@NonNull DomainFilterMatcher matcher, @NonNull String path);
    void onStart(@NonNull DomainFilterMatcher matcher);
}
