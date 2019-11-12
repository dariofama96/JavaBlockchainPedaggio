package httpserver;

import java.util.Date;

public class Block {
    
        public String hash;
	public String previousHash;
	private final String data; 
	private final long timeStamp; //numero di millisecondi.

	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
                this.hash = calculateHash();
	}
        
        public String calculateHash() {
            String calculatedhash = SHA256.applySha256( 
                            previousHash +
                            Long.toString(timeStamp) +
                            data 
                            );
            return calculatedhash;
        }

}
