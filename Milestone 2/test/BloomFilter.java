
package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BloomFilter
{

    private BitSet bitSet;
    private int bitSetSize;
    private List<MessageDigest> digests;
  public BloomFilter(int size, String... hashAlgos)
  {
      bitSetSize = size;
      bitSet = new BitSet(size);
      digests = new ArrayList<>();
      for (String hashAlgo: hashAlgos)
      {
          try {
              digests.add(MessageDigest.getInstance(hashAlgo));
          }
          catch (NoSuchAlgorithmException e)
          {
              throw new RuntimeException("failed to get instance of MessageDigest for algo named: "
                      + hashAlgo, e);
          }
      }
  }

  public void add(String word)
  {
      byte[] wordCurDigestBytes;
      byte[] wordBytes = word.getBytes();

      for (MessageDigest curDigest : digests)
      {
          wordCurDigestBytes = curDigest.digest(wordBytes);
          BigInteger wordCurDigestBigInt = new BigInteger(wordCurDigestBytes);
          int wordCurDigestInd = Math.abs(wordCurDigestBigInt.intValue());
          bitSet.set(wordCurDigestInd % bitSetSize);
      }
  }

    public boolean contains(String word)
    {
        byte[] wordCurDigestBytes;
        byte[] wordBytes = word.getBytes();

        for (MessageDigest curDigest : digests)
        {
            wordCurDigestBytes = curDigest.digest(wordBytes);
            BigInteger wordCurDigestBigInt = new BigInteger(wordCurDigestBytes);
            int wordCurDigestInd = Math.abs(wordCurDigestBigInt.intValue());
            if (!bitSet.get(wordCurDigestInd % bitSetSize))
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {

        int lastInd = bitSetSize -1;
        while (!bitSet.get(lastInd))
            lastInd--;

        char[] str = new char[lastInd+1];
        for (int i = 0; i <= lastInd; i++) {
            str[i] = bitSet.get(i) ? '1' : '0';
        }
        return String.valueOf(str);
    }

}
