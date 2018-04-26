package ru.sberbank.final_task.babbler.service;

import ru.sberbank.final_task.babbler.domain.File;

public interface FileService {

//    void saveFiles(Set<File> files);

    void saveFile(File file);

//    void deleteFiles(Set<File> files, Message message);

//    Set<File> getFiles(Message message);

    File getFile(Long id);

}
