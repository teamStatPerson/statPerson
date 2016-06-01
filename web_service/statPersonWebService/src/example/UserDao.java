package example;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
   public List<User> getAllUsers(){
      List<User> userList = null;
      try {
         File file = new File("Users.dat");
         if (!file.exists()) {
            userList = new ArrayList<User>();
            userList.add(new User(1, "Mahesh", "Teacher"));
            userList.add(new User(2, "Mahesh2", "Teacher2"));
            userList.add(new User(3, "Mahesh3", "Teacher3"));
            saveUserList(userList);		
         }
         else{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (List<User>) ois.readObject();
            ois.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }		
      return userList;
   }

   public User getUser(int id){
      List<User> users = getAllUsers();

      for(User user: users){
         if(user.getId() == id){
            return user;
         }
      }
      return null;
   }

   public int addUser(User pUser){
      List<User> userList = getAllUsers();
      boolean userExists = false;
      for(User user: userList){
         if(user.getId() == pUser.getId()){
            userExists = true;
            break;
         }
      }		
      if(!userExists){
         userList.add(pUser);
         saveUserList(userList);
         return 1;
      }
      return 0;
   }

   public int updateUser(User pUser){
      List<User> userList = getAllUsers();

      for(User user: userList){
         if(user.getId() == pUser.getId()){
            int index = userList.indexOf(user);			
            userList.set(index, pUser);
            saveUserList(userList);
            return 1;
         }
      }		
      return 0;
   }

   public int deleteUser(int id){
      List<User> userList = getAllUsers();

      for(User user: userList){
         if(user.getId() == id){
            int index = userList.indexOf(user);			
            userList.remove(index);
            saveUserList(userList);
            return 1;   
         }
      }		
      return 0;
   }

   private void saveUserList(List<User> userList){
      try {
         File file = new File("Users.dat");
         FileOutputStream fos;

         fos = new FileOutputStream(file);

         ObjectOutputStream oos = new ObjectOutputStream(fos);		
         oos.writeObject(userList);
         oos.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}