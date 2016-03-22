/**
 * 
 */
package personalNameExtractor;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author addy Alago
 * User story 854: As a Librarian, I want miscellaneous words such as honorifics and kill words to be identified correctly.
 */
public class TestPersonalName
{

  /**
   * Tests default contructor
   */
  @Test
  public void testPersonalName()
  {
    PersonalName name = new PersonalName();

    assertEquals(name, null);
  }

  /**
   * Tests constructor with String parameter
   */
  @Test
  public void testPersonalNameString()
  {

    PersonalName name = new PersonalName("Mr. Addy Alago,Jr.");

    assertEquals(name.getName(),"Mr. Addy Alago,Jr.");

  }

  /**
   * Test method for getting names with and without suffixes and middle names
   */
  @Test
  public void testGetName()
  {

    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Mr. Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name, null);
    assertEquals(name1, "Mr. Addy Alberto Alonzo Alago,Jr.");
    assertEquals(name2, "Mr. Addy Alberto Alago");
    assertEquals(name3, "Addy Alberto Alago,Jr.");
    assertEquals(name4, "Addy Alago");

  }

  /**
   * Test method for getting Honorific from name
   */
  @Test
  public void testGetHonorific()
  {

    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name.getHonorific(), null);
    assertEquals(name1.getHonorific(), null);
    assertEquals(name2.getHonorific(), "Mr.");
    assertEquals(name3.getHonorific(), null);
    assertEquals(name4.getHonorific(), null);
  }

  /**
   * Test method for getting first (Given) name from names.
   */
  @Test
  public void testGetGivenName()
  {
    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name.getGivenName(), null);
    assertEquals(name1.getGivenName(), "Addy");
    assertEquals(name2.getGivenName(), "Addy");
    assertEquals(name3.getGivenName(), "Addy");
    assertEquals(name4.getGivenName(), "Addy");
  }

  /**
   * Test method for getting all middle name from names.
   */
  @Test
  public void testGetMiddleNames()
  {
    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name.getMiddleNames(), null);
    assertEquals(name1.getMiddleNames(), "Alberto Alonzo");
    assertEquals(name2.getMiddleNames(), "Alberto");
    assertEquals(name3.getMiddleNames(), "Alberto");
    assertEquals(name4.getMiddleNames(), null);
  }

  /**
   * Test method for getting surname from names.
   */
  @Test
  public void testGetSurName()
  {
    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name.getSurName(), null);
    assertEquals(name1.getSurName(), "Alago");
    assertEquals(name2.getSurName(), "Alago");
    assertEquals(name3.getSurName(), "Alago");
    assertEquals(name4.getSurName(), "Alago");
  }

  /**
   * Test method for getting suffixes from names.
   */
  @Test
  public void testGetSuffix()
  {
    PersonalName name = new PersonalName();
    // default constructor
    PersonalName name1 = new PersonalName("Addy Alberto Alonzo Alago,Jr.");
    // prefix , suffix, middle names
    PersonalName name2 = new PersonalName("Mr. Addy Alberto Alago");
    // prefix no suffix
    PersonalName name3 = new PersonalName("Addy Alberto Alago,Jr.");
    // suffix no prefix
    PersonalName name4 = new PersonalName("Addy Alago");
    // No middle name

    assertEquals(name.getSuffix(), null);
    assertEquals(name1.getSuffix(), "Jr.");
    assertEquals(name2.getSuffix(), null);
    assertEquals(name3.getSuffix(), "Jr.");
    assertEquals(name4.getSuffix(), null);
  }

}
