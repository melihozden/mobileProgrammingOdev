package com.melihozden.mobileprogrammingodev;

import java.util.ArrayList;
public class User {

    private String userName ;
    private String userPassword ;
    private int imageID ;
/*
    private String nickName ;
    private boolean gender ;

    private String boy ;
    private String kilo ;
    private String yas ;

    private boolean appMode ;
*/
    public User(int imageID, String userName,String userPassword){
        this.userName = userName ;
        this.userPassword = userPassword;
        this.imageID = imageID ;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public int getImageID() {
        return imageID;
    }

    public static ArrayList<User> getData(){
        ArrayList<User> userList = new ArrayList<User>() ;

        int userImage[] = {R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user5,R.drawable.user6,R.drawable.user7,R.drawable.user8,R.drawable.user9};

        String[] userNames = {"Melih Ozden","Arda Dizdaroglu","Gokhan Ata","M.Amac Guvensan","Cahid Topkaraoglu","Melih Ozden","Melih Ozden","Melih Ozden","Melih Ozden"};
        String[] userPasswords = {"123456","asfnaf","asfnjaskfkjna","kjsafasnfjnajsf","aklfanfklf","alsnfansfnl","lknasflasnf","lkasnfksanfnf","lkanfsaasflk"} ;

        for(int i = 0 ;i < userNames.length; i++){
            User user = new User(userImage[i],userNames[i],userPasswords[i]) ;
            userList.add(user) ;
        }
        return userList ;

    }


}
