package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Model.ImportHistory;
import com.JEEProject.TableStore.repositories.ImportHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ImportHistoryService {
    private final ImportHistoryRepository importHistoryRepository;
    public ImportHistory getImportHistory(User user){
        return importHistoryRepository.save(ImportHistory.builder().user(user).timeImport(new Date()).build());
    }
}
