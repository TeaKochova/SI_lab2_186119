import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private SILab2 s=new SILab2();

    private List<String> createList(String... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }

    @Test
    void everyBranchTest()
    {
        RuntimeException ex = null;
        try {
            s.function(null,null);
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("The user argument is not initialized!"));

        User user=new User("Tea","0000","teakocova@gmail.com");
        try{
            s.function(user,createList("Tea","0000","teakocova@gmail.com"));
        } catch (RuntimeException e){
            ex=e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));

        User user1=new User("Tea","0000",null);
        assertFalse(s.function(user1, createList("Filip","2222","filip@yahoo.com")));

        User user2=new User("Tea","0000","tk@gmail.com");
        assertTrue(s.function(user2, createList("Filip","2222","filip@yahoo.com")));

        User user3=new User("Tea","0000","gmail.com");
        assertFalse(s.function(user3, createList("Filip","2222","filip@yahoo.com")));
    }

    @Test
    void multipleConditionsTest() {
        //user.getUsername() == null || allUsers.contains(user.getUsername())

        RuntimeException ex = null;
        //T || X
        User user1=new User(null,"0000","teakocova@gmail.com");
        try{
            s.function(user1,null);
        } catch (RuntimeException e){
            ex=e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));

        //F || T
        User user=new User("Tea","0000","teakocova@gmail.com");
        try{
            s.function(user,createList("Tea","0000","teakocova@gmail.com"));
        } catch (RuntimeException e){
            ex=e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));


        //F || F
        User user2=new User("Tea","0000","teakocova@gmail.com");
        assertEquals(true,s.function(user2,createList("Filip","2222","filip@yahoo.com")));

        // --------------------

        //atChar && user.getEmail().charAt(i) == '.'

        //T && T
        User user3=new User("Tea","0000","teakocova@gmail.com");
        assertEquals(true,s.function(user3,createList("Filip","2222","filip@yahoo.com")));

        //T && F
        User user4=new User("Tea","0000","teakocova@gmailcom");
        assertEquals(false,s.function(user4,createList("Filip","2222","filip@yahoo.com")));

        //F && F
        User user5=new User("Tea","0000","gmail.com");
        assertEquals(false,s.function(user5,createList("Filip","2222","filip@yahoo.com")));

        // --------------------
        //!atChar || !dotChar

        //T || X
        User user6=new User("Tea","0000","gmail.com");
        assertEquals(false,s.function(user6,createList("Filip","2222","filip@yahoo.com")));

        //F || T
        User user7=new User("Tea","0000","teakocova@gmailcom");
        assertEquals(false,s.function(user7,createList("Filip","2222","filip@yahoo.com")));

        //F || F
        User user8=new User("Tea","0000","teakocova@gmail.com");
        assertEquals(true,s.function(user8,createList("Filip","2222","filip@yahoo.com")));

    }
}