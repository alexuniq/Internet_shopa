package application.services;

import application.bd.Database;

public class ClearUIActionService {

    private final Database db;

    public ClearUIActionService(Database db) {
        this.db = db;
    }

    public void clearProductList(String name, String Id, double price) {
        db.clear();
    }

}
