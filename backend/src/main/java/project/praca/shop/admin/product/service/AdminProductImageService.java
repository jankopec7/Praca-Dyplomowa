package project.praca.shop.admin.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import project.praca.shop.admin.common.utils.SlugifyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminProductImageService {

    @Value("${app.uploadDir}")
    private String uploadDir;

    public String uploadImage(String filename, InputStream inputStream) {
        String newFileName = SlugifyUtils.slugifyFileName(filename);
        newFileName = ExistingFileRenameUtils.renameIfExists(Path.of(uploadDir), newFileName);
        Path filePath = Paths.get(uploadDir).resolve(newFileName);
        try(OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot save the file", e);
        }
        return newFileName;
    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + filename);
    }
}