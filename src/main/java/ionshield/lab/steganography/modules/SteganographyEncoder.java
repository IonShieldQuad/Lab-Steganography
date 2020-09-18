package ionshield.lab.steganography.modules;

public interface SteganographyEncoder {
    String encode(String base, String message) throws EncodingException;
}
