package top.yuan.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
