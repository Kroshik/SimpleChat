package ru.sberbank.final_task.babbler.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.File;
import ru.sberbank.final_task.babbler.repository.FileRepository;
import ru.sberbank.final_task.babbler.service.FileService;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

//    @Override
//    public void saveFiles(Set<File> files) {
//        files.forEach(file -> fileRepository.save(file));
//    }

    @Override
    public void saveFile(File file) {
        fileRepository.save(file);
    }

//    @Override
//    public void deleteFiles(Set<File> files, Message message) {
//
//    }

//    @Override
//    public File getFiles(Message message) {
//        return fileRepository.findByMessage(message);
//    }

    @Override
    public File getFile(Long id) {
        return fileRepository.getOne(id);
    }
}

