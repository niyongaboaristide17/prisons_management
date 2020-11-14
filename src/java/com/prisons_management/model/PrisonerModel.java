/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.PrisonerDao;
import com.prisons_management.domain.Prison;
import com.prisons_management.domain.Prisoner;
import com.prisons_management.domain.PrisonerStatus;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.Part;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author aristide
 */
@ManagedBean
@SessionScoped
public class PrisonerModel {

    /**
     * Creates a new instance of PrisonerModel
     */
    private Part uploadedFile;
    
    private Prisoner prisoner = new Prisoner();
    

    public PrisonerModel() {
        
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void upload(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if(uploadedFile != null){
            try(InputStream input = uploadedFile.getInputStream()){
                String fileName = uploadedFile.getSubmittedFileName();
//                System.out.println(fileName.split(".")[1]);
//                String ext = fileName.split(".")[1];
                String pp = "pp"+UUID.randomUUID().toString().replace("-","")+".png";
                prisoner.setProfilePicture(pp);
                String savelocation = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "prisonerprofileImages";
                
                File old = new File(fileName);
                File newF = new File(pp);
                
                Files.copy(input, new File(savelocation, pp ).toPath());
                prisoner.setProfilePicture(pp);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public String addPrisoner(String prisonerCode){
        String msg;
        try {
            prisoner.setPrison(new Prison(prisonerCode));
            msg= PrisonerDao.createPrisoner(prisoner);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(prisoner.getPrisonerName()+" Added"));
            prisoner = new Prisoner();
            return "addPrisoner.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something went wrong"));
            return "addPrisoner.xhtml?faces-redirect=true";
        }
    }
    
    public List<Prisoner> prisonerList(String prisonCode){
        
        return PrisonerDao.allPrisonerInAprison(prisonCode);
    }
    
    public List<Prisoner> prisonerListIn(String prisonCode){
        List<Prisoner> l = new ArrayList<>();
        for (Prisoner p : PrisonerDao.allPrisonerInAprison(prisonCode)) {
            if (p.getPrisonerStatus() == PrisonerStatus.JAILED) {
                l.add(p);
            }
        }
        return l;
    }
    
    public String releasePrisoner(String prisonerId){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            String msg = PrisonerDao.updateStatus(prisonerId);
            fc.addMessage(null, new FacesMessage(msg));
            System.out.println(prisonerId);
        } catch (Exception e) {
            e.printStackTrace();
            fc.addMessage(null, new FacesMessage("Something went wrong"));
        }
        return "/admin/managePrisoners.xhtml?faces-redirect=true";
    }

}
