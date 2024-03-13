package tyut.selab.suggestion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.suggestion.domain.AESEncryptionExample;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;
import tyut.selab.suggestion.mapper.SuggestionMapper;
import tyut.selab.suggestion.service.ISuggestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SuggestionServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:35
 * @Version: 1.0
 **/
@Service
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, SuggestionEntity> implements ISuggestionService {
    @Autowired
    private SuggestionMapper suggestionMapper;
    @Override
    public AjaxResult getallSuggestion(PageParam pageParam){
        List<SuggestionEntity> suggestionList = suggestionMapper.selectAllSuggestion();
        suggestionList.remove(0);
        List<SuggestionEntity> suggestionList1 = new ArrayList<SuggestionEntity>();
        for (SuggestionEntity suggestion : suggestionList){
            System.out.println(suggestion);
            try {
                suggestion.setSuggestionContent(AESEncryptionExample.decrypt(suggestion.getSuggestionContent(),"admin"));
                suggestion.setSuggestionUser(AESEncryptionExample.decrypt(suggestion.getSuggestionUser(),"admin"));
                suggestionList1.add(suggestion);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());

// 使用PageInfo对查询结果进行包装，获取分页相关信息
        PageInfo<SuggestionEntity> suggestionIPage = new PageInfo<>(suggestionList1);
        // 将查询结果的List赋值给Page对象的构造函数
        return AjaxResult.success(suggestionIPage);
    }
    @Override
    public AjaxResult addSuggestion(SuggestionEntity suggestion) {
        suggestion.setSuggestionUser(SecurityUtils.getUsername());
        SuggestionEntity suggestionEntity =suggestionMapper.selectSuggestionById(1);
        String key = suggestionEntity.getSuggestionContent();
        String suggestionContent =suggestion.getSuggestionContent();
        String suggestionUser = suggestion.getSuggestionUser();
        try {
            suggestion.setSuggestionContent(AESEncryptionExample.encrypt(suggestionContent,key));
            System.out.println(AESEncryptionExample.encrypt(suggestionContent,key));
            suggestion.setSuggestionUser(AESEncryptionExample.encrypt(suggestionUser,key));
            System.out.println(AESEncryptionExample.encrypt(suggestionUser,key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        suggestionMapper.insertSuggestion(suggestion);
        return AjaxResult.success("添加成功！");
    }
    @Override
    public AjaxResult verifySuggestionKey(String suggestionKey){
        SuggestionEntity suggestionEntity =suggestionMapper.selectSuggestionById(1);
        if(suggestionEntity.getSuggestionContent().equals(AESEncryptionExample.Encrypt(suggestionKey))){
            return AjaxResult.success("验证成功！");
        }else {
            return AjaxResult.error();
        }
    }
//    @Override
//    public AjaxResult reviseSuggestionKey(String suggestionKey){
//        suggestionMapper.insert(suggestion);
//        return AjaxResult.success("添加成功！");
//    }
}
