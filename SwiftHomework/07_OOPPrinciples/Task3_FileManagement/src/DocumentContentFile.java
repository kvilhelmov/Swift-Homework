class DocumentContentFile extends ContentFile{

    public DocumentContentFile(String name, String location, String content) {
        super(name, location, content);
        super.symbol = '\u2713';
    }
    
}