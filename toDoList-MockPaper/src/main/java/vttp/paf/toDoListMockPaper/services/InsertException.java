package vttp.paf.toDoListMockPaper.services;

import java.io.IOException;

public class InsertException extends IOException{
    
    public InsertException(){
        super();
    }

    public InsertException(String errorMsg){
        super(errorMsg);
    }
}
