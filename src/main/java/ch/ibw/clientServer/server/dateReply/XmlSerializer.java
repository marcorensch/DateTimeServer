package ch.ibw.clientServer.server.dateReply;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class XmlSerializer {

    public void serialize(Object obj, String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        String serialized = mapper.writeValueAsString(obj);
        Files.write(Paths.get(fileName), serialized.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

    public String serialize(Object obj) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(obj);
    }

    public <T> T deserialize(String fileName, TypeReference<T> ref) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(new File(fileName), ref);
    }
}
