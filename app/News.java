public class News {
    public final String author;
    public final String title;
    public final String description;
    public final String url;
    public final String urlToImage;
    public final String content;
    public final Date publishedAt;

    public News(
            String author,
            String title,
            String description,
            String url,
            String urlToImage,
            String content,
            String publishedAt
    ) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.publishedAt = publishedAt;
    }







}

