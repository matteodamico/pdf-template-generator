package it.mattdmc.pdfcreator.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.Map;

public interface Pdfcreator {
    Resource fill(MultipartFile file, Map<String,String> params);
}
