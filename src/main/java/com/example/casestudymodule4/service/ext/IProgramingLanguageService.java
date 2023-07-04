package com.example.casestudymodule4.service.ext;

import com.example.casestudymodule4.model.ProgramingLanguage;
import com.example.casestudymodule4.service.core.ICoreService;

public interface IProgramingLanguageService extends ICoreService<ProgramingLanguage>
{
    Iterable<ProgramingLanguage> findAll();
}
