package input;

import identification.Identification;

import java.io.IOException;

public interface IdentReader {
    Identification readIdent() throws IOException;
}
