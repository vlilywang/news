exports.mysql = {
    client: {
        host: 'localhost',
        port: '3306',
        user: 'other',
        password: '112358',
        database: 'news',
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