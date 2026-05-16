# Template Source Examples

## 中文说明

`source/` 下每个目录都是一个完整模块示例。

### `my_module`

推荐作为大多数模块的起点，展示：

- `invoke(Context, action, paramsJson)` 中的 Action 路由。
- JSON 参数安全解析。
- 结构化业务输出。
- `_displayText` 文本兜底。
- `HtmlOutputHelper` 生成 `_displayHtml`。
- `ModuleStorage` 模块私有存储。
- 通过 `_openModule` 打开 H5 页面。

可测试提示词：

- `用 my module 对 Alice 说 hello`
- `用 my module 处理 hello panda`
- `保存偏好 theme=dark`
- `读取偏好 theme`
- `打开 my module 页面`

### `network_demo`

适合需要 HTTP/API 的模块，展示：

- `capabilities: ["network"]`
- `android.permission.INTERNET`
- URL scheme 校验。
- 连接和读取超时。
- 有长度限制的响应预览。
- HTML 摘要输出。

可测试提示词：

- `用 network demo 获取 https://httpbin.org/json`
- `用 network demo 访问 https://example.com 并展示预览`

## 能力声明建议

不要提前声明不使用的能力。只有真实访问用户文件、网络、定位、联系人、日历、剪贴板、麦克风或 AI 模型时才声明对应能力。模块私有数据使用 `ModuleStorage` 即可，不需要文件读写权限。

---

## English

Every folder under `source/` is a complete module example.

### `my_module`

Recommended starter for most developers. It demonstrates action routing, JSON parameter parsing, structured output, `_displayText`, `_displayHtml`, module-private storage, and opening an H5 page with `_openModule`.

### `network_demo`

Starter for modules that need HTTP/API access. It demonstrates `network` capability declaration, Android `INTERNET` permission, URL validation, timeouts, bounded response previews, and HTML summary output.

Only declare capabilities that your module actually uses.
