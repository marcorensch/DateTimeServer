package ch.ibw.clientServer.server.javaReply;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XmlSerializer {

    public String serialize(Object obj) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(obj);
    }
}
