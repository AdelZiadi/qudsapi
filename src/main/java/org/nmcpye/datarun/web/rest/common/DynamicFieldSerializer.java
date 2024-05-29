package org.nmcpye.datarun.web.rest.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DynamicFieldSerializer<T> extends StdSerializer<PagedResponse<T>> {

    public DynamicFieldSerializer() {
        this(null);
    }

    public DynamicFieldSerializer(Class<PagedResponse<T>> t) {
        super(t);
    }

    @Override
    public void serialize(PagedResponse<T> pagedResponse, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField("paging", pagedResponse.isPaging());
        gen.writeNumberField("page", pagedResponse.getPage());
        gen.writeNumberField("pageCount", pagedResponse.getPageCount());
        gen.writeNumberField("total", pagedResponse.getTotal());
        gen.writeNumberField("pageSize", pagedResponse.getPageSize());
        if(pagedResponse.getNextPage() != null) {
            gen.writeStringField("nextPage", pagedResponse.getNextPage());
        }
        String entityName = pagedResponse.getEntityName();
        gen.writeFieldName(entityName);
        provider.defaultSerializeValue(pagedResponse.getItems(), gen);

        gen.writeEndObject();
    }
}
