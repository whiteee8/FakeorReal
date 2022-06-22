package com.study.realworld.service.article;

import com.study.realworld.domain.article.Article;
import com.study.realworld.domain.article.ArticleRepository;
import com.study.realworld.domain.article.Image;
import com.study.realworld.domain.article.ImageRepository;
import com.study.realworld.domain.vote.Vote;
import com.study.realworld.domain.vote.VoteRepository;
import com.study.realworld.web.dto.article.ArticleRequestDto;
import com.study.realworld.web.dto.article.ArticleSaveRequestDto;
import com.study.realworld.web.dto.article.ArticleUpdateRequestDto;
import com.study.realworld.web.dto.article.ImageSaveRequestDto;
import com.study.realworld.web.handler.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final VoteRepository voteRepository;

    private final ImageHandler imageHandler;

    @Transactional
    public Long save(ArticleSaveRequestDto requestDto, MultipartFile file) throws Exception {
        return imageRepository.save(imageHandler.parseFileInfo(file, articleRepository.save(requestDto.toEntity()).getId())).getOrderId();
    }

    @Transactional
    public Long save(ArticleSaveRequestDto requestDto, String img) throws Exception {
        return imageRepository.save(new ImageSaveRequestDto(img, articleRepository.save(requestDto.toEntity()).getId()).toEntity()).getOrderId();
    }

    @Transactional
    public Long save(ArticleSaveRequestDto requestDto) throws Exception {
        return articleRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ArticleUpdateRequestDto requestDto) {
        Article Article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        Article.update(Optional.ofNullable(requestDto.getSlug()), Optional.ofNullable(requestDto.getTitle()), Optional.ofNullable(requestDto.getDescription()), Optional.ofNullable(requestDto.getBody()));
        return id;
    }

    public ArticleRequestDto findById(Long id) {
        Article entity = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        Image image = imageRepository.findByOrderId(id);
        Optional<Vote> vote = voteRepository.findByArticleId(id);

        return new ArticleRequestDto(entity, entity.getImg(), vote);
    }

    public List<ArticleRequestDto> findByKind(short kind) {
        List<Article> entity = articleRepository.findByKind(kind);

        List<ArticleRequestDto> articleRequestDtoList = new ArrayList<>();
        for (Article e : entity) {
            Image image = imageRepository.findByOrderId(e.getId());
            Optional<Vote> vote = voteRepository.findByArticleId(e.getId());
            articleRequestDtoList.add(new ArticleRequestDto(e, e.getImg(), vote));
        }

        return articleRequestDtoList;
    }
}
