module.exports = app => {
    return class NewsService extends app.Service {
        async getNewById(id) {
            const neww = await this.app.mysql.get('news', { id: id });
            if (!neww) {
                return 0;
            } else {
                return { neww };
            }
        }
        async getNewsList() {
            const news = await this.app.mysql.select('news');
            return news;
        }

    }
}