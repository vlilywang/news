exports.mysql = {
    client: {
        host: '10.18.43.12',
        port: '3306',
        user: 'root',
        password: 'root',
        database: 'news_wll',
    },
    app: true,
    agent: false,
};
exports.keys = '_test';
// 添加 view 配置
const path = require('path');
exports.view = {
    defaultViewEngine: 'nunjucks',
    mapping: {
        '.tpl': 'nunjucks',
    },
};