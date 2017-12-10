package com.etfdoc.etfdoc.Controllers;

import com.etfdoc.etfdoc.Services.DocumentService;
import com.etfdoc.etfdoc.ViewModels.DocumentVM;
import com.etfdoc.etfdoc.ViewModels.FolderVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/document")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService){
        this.documentService = documentService;
    }

    @RequestMapping(value = "/create", consumes = "application/json", method = RequestMethod.POST )
    public ResponseEntity createFolder(@RequestBody DocumentVM documentVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(documentService.createDocument(documentVM));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByRootAndOwner", method = RequestMethod.GET)
    public ResponseEntity getDocumentsByOwnerAndRoot(@RequestParam String email){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(documentService.getAllByOwnerAndRoot(email));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByOwnerAndFolder", method = RequestMethod.GET)
    public ResponseEntity getFoldersByParent(@RequestParam String email, Long folderID){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(documentService.getAllByOwnerAndFolder(email,folderID));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByRootAndPublic", method = RequestMethod.GET)
    public ResponseEntity getRootAndPublic(){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(documentService.getAllRootAndPublic());

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }


}
