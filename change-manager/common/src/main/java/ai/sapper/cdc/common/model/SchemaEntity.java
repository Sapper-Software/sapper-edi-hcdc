package ai.sapper.cdc.common.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class SchemaEntity {
    private String domain;
    private String schema = "DEFAULT";
    private String group = "DEFAULT";
    private String entity;

    public SchemaEntity() {
    }

    public SchemaEntity(@NonNull String domain, @NonNull String entity) {
        this.domain = domain;
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemaEntity that = (SchemaEntity) o;
        return domain.equals(that.domain) && Objects.equals(group, that.group) && entity.equals(that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, group, entity);
    }
}
