package ${package}.persistence.common.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class JsonNodeDatabaseType implements UserType<JsonNode> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int getSqlType() {
        return Types.JAVA_OBJECT;
    }

    @Override
    public Class<JsonNode> returnedClass() {
        return JsonNode.class;
    }

    @Override
    public boolean equals(JsonNode x, JsonNode y) {
        if (x == null || y == null) {
            return false;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(JsonNode x) {
        return Objects.hashCode(x);
    }

    @Override
    public JsonNode nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String content = rs.getString(position);
        if (content != null) {
            try {
                return objectMapper.readValue(content, returnedClass());
            } catch (JsonProcessingException e) {
                throw new HibernateException("Failed to convert String to JsonNode: " + content, e);
            }
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, JsonNode value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            try {
                st.setObject(index, objectMapper.writeValueAsString(value), Types.OTHER);
            } catch (JsonProcessingException e) {
                throw new HibernateException("Failed to convert JsonNode to String: " + value, e);
            }
        } else {
            st.setNull(index, Types.OTHER);
        }
    }

    @Override
    public JsonNode deepCopy(JsonNode value) {
        if (value == null) {
            return null;
        }
        return value.deepCopy();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(JsonNode value) {
        return null;
    }

    @Override
    public JsonNode assemble(Serializable cached, Object owner) {
        return null;
    }
}
