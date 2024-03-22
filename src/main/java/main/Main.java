package main;


import pages.Login;
import testCases.LoginToDaraz;
import utils.GetUserRows;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Object[]> userData = GetUserRows.getUserRows();
        LoginToDaraz login = new LoginToDaraz();
        assert userData != null;
        login.loginToDaraz(userData);
    }
}