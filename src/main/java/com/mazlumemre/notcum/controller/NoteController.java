package com.mazlumemre.notcum.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mazlumemre.notcum.entity.Note;
import com.mazlumemre.notcum.entity.User;
import com.mazlumemre.notcum.service.NoteService;

import jakarta.servlet.http.HttpSession;


//               http://localhost:8080/note
@RestController
@RequestMapping(path = "/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    // not sil
    @DeleteMapping(path = "/del")
    public ResponseEntity<String> deleteNote(@RequestBody Note note) {
        noteService.deleteNote(note);
        return ResponseEntity.ok("Not başarıyla silindi!");
    }

    @GetMapping("/get-all")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        // Veritabanından notu bulup döndürme
        Note note = noteService.getNoteById(id);

        if (note != null) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/note-detail")
    public String noteDetailPage(@RequestParam Long id, Model model) {
        // id'ye göre notu veritabanından al
        Note note = noteService.getNoteById(id);
        // Not bilgilerini model'e ekle
        model.addAttribute("note", note);
        return "note-detail";
    }

    @GetMapping("/view-pdf/{id}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {
        try {
            // Veritabanından PDF dosyasını al
            Note note = noteService.getNoteById(id);
            byte[] pdfBytes = note.getFile();

            if (pdfBytes == null) {
                return ResponseEntity.notFound().build();
            }

            // PDF yanıtını oluştur
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename(note.getTitle() + ".pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadNote(@RequestParam("courseName") String courseName,
                                             @RequestParam("department") String department,
                                             @RequestParam("courseCode") String courseCode,
                                             @RequestParam("description") String description,
                                             @RequestParam("price") String price,
                                             @RequestParam("file") MultipartFile file, HttpSession session) {
        try {
            User loggedUser = (User) session.getAttribute("loggedUser");

            // Note nesnesi oluştur
            Note note = new Note();
            note.setTitle(courseName);
            note.setDepartment(department);
            note.setCode(courseCode);
            note.setDescription(description);
            note.setPrice(price);
            note.setFile(file.getBytes());

            // Kullanıcı bilgisi ekleyin
            note.setUser(loggedUser);

            // Notu veritabanına kaydet
            noteService.saveNote(note);

            return ResponseEntity.ok("Not başarıyla yüklendi!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Bir hata oluştu: " + e.getMessage());
        }
    }


}
