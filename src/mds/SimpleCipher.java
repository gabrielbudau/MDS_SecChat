package mds;

/**
 *
 * @author Gabriel Budau
 */
public abstract class SimpleCipher {
 
    private char[] VALID_LETTER = "abcdefghijklmnopqrstuvwxyz".toCharArray();
 
    public abstract String encrypt(String plainText);
 
    public abstract String decrypt(String encypted);
 
 
    protected int getValidCharacterIndex(char c) {
        for (int i = 0; i < VALID_LETTER.length; i++) {
            if (c == VALID_LETTER[i]) {
                return i;
            }
        }
        return -1;
    }
 
    protected char getValidCharacterForIndex(int i){
        return VALID_LETTER[i];
    }
}
