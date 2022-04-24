package dev.liambloom.util;

import java.io.IOException;
import java.io.OutputStream;

public class MultiplexOutputStream extends OutputStream {
    private OutputStream[] streams;

    public MultiplexOutputStream(OutputStream... streams) {
        this.streams = streams;
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (OutputStream stream : streams)
            stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream stream : streams)
            stream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream stream : streams)
            stream.write(b);
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream stream : streams)
            stream.flush();
    }

    @Override
    public void close() throws IOException {
        for (OutputStream stream : streams)
            stream.close();
    }
}
