<div align="center">

# PandaGenie Module Template

**PandaGenie 热加载模块开发模板**

[使用模板](https://github.com/Rorschach123/PandaGenie-Module-Template/generate) | [官网](https://cf.pandagenie.ai) | [模块提交](https://cf.pandagenie.ai/sign) | [PandaGenieSource](https://github.com/Rorschach123/PandaGenieSource) | [Discord](https://discord.gg/Cfc7pjrjt2)

</div>

---

## 中文说明

这个仓库用于开发 PandaGenie 内部热加载模块。模块会被 PandaGenie App 安装、加载，并在用户通过自然语言提出任务时被 LLM 调度执行。

如果你想让一个独立 Android App 被 PandaGenie 调用，请使用 [PandaGenieSDK](https://github.com/Rorschach123/PandaGenieSDK) 和 [SDK Provider 模板](https://github.com/Rorschach123/PandaGenieSDK-Provider-Template)。如果你想给 PandaGenie 增加一个内部能力模块，就从这个模板开始。

## 快速开始

1. 点击 GitHub 上的 **Use this template** 创建自己的模块仓库。
2. 从 `source/my_module` 复制一个最小模块。
3. 修改 `manifest.json` 中的 `id`、`name`、`description`、`apis`、`capabilities` 和中英文描述。
4. 在 `plugin_src/.../MyModulePlugin.java` 中实现 `invoke()`。
5. 使用 PandaGenieSource 的 `module-dev-toolkit` 打包、签名、安装测试。
6. 到 [模块提交页](https://cf.pandagenie.ai/sign) 上传发布。

## 模板示例

| 示例 | 适用场景 | 展示内容 |
|---|---|---|
| `source/my_module` | 大多数模块的安全起点 | Action 路由、参数解析、私有存储、`_displayHtml`、打开 H5 页面。 |
| `source/network_demo` | 需要访问 HTTP/API 的模块 | `network` 能力声明、`INTERNET` 权限、URL 校验、超时和结果预览。 |

## 模块结构

```text
source/my_module/
├── manifest.json
├── index.html
└── plugin_src/
    └── ai/rorsch/moduleplugins/my_module/
        └── MyModulePlugin.java
```

## 核心接口

```java
public interface ModulePlugin {
    String invoke(Context context, String action, String paramsJson) throws Exception;
}
```

推荐返回结构：

```json
{
  "success": true,
  "output": "{\"count\":2,\"items\":[\"a\",\"b\"]}",
  "_displayText": "已处理 2 项。",
  "_displayHtml": "<div class='pg-card'>...</div>"
}
```

常用返回字段：

| 字段 | 作用 |
|---|---|
| `_displayText` | 普通文本兜底展示。 |
| `_displayHtml` | 对话中的富文本卡片。 |
| `_displayHtmlFull` | 详情页完整内容。 |
| `_openModule` | 打开模块 H5 页面。 |
| `_richContent` | 返回图片、文件等富内容。 |
| `_vaultSave` | 提供保存到保险箱的内容。 |

## 能力声明

模块只声明真实需要的能力。不要为了方便一次性声明所有权限。

| 能力 | 何时使用 |
|---|---|
| `network` | 访问互联网或局域网接口。 |
| `file_read` / `file_write` | 读取或写入用户可见文件。 |
| `llm` | 需要 PandaGenie 代为调用当前 AI 模型。 |
| `camera`, `location`, `contacts`, `calendar`, `clipboard`, `microphone` | 调用对应 Android 能力时声明。 |

模块私有数据建议使用 `ModuleStorage`，不需要声明文件读写权限。

## 模块调用 AI 模型

需要摘要、改写、分类、提取、翻译或文档理解时，模块可以声明 `llm` 能力并调用 App 提供的 LLM 接口。

```json
"capabilities": ["llm"]
```

```java
String resultJson = ModuleLlm.completeJson(
    context,
    new JSONObject()
        .put("prompt", "用三条要点总结这段内容：" + text)
        .put("action", "summarize")
        .put("maxTokens", 512)
        .put("temperature", 0.2)
        .toString()
);
```

规则：

- 未声明 `llm` 的模块会被 App 拦截。
- 用户需要授予模块 AI 模型权限。
- PandaGenie Official 的模块 LLM 调用会计入单独的模块模型用量。
- 单次请求会被服务端限制 token 数，避免失控消耗。

## 打包测试

```powershell
git clone https://github.com/Rorschach123/PandaGenieSource.git
cd PandaGenieSource\module-dev-toolkit
.\mk_module.ps1 -Action init-dev-signing
.\mk_module.ps1 -Action pack -Modules "my_module"
adb push ..\modules\my_module.mod /sdcard/PandaGenie/modules/
```

在 PandaGenie 中打开开发者模式并重启 App 后即可测试模块。

---

## English

This repository is the starter template for PandaGenie hot-loadable modules. Use it when you want to add an internal PandaGenie module that can be planned and executed by the assistant.

If you want an independent Android app to be callable by PandaGenie, use [PandaGenieSDK](https://github.com/Rorschach123/PandaGenieSDK) and the [SDK Provider Template](https://github.com/Rorschach123/PandaGenieSDK-Provider-Template) instead.

Quick path:

1. Create a repository from this template.
2. Copy `source/my_module`.
3. Update `manifest.json` with IDs, APIs, parameters, capabilities, and bilingual descriptions.
4. Implement `invoke()` in your plugin class.
5. Build and sign through `PandaGenieSource/module-dev-toolkit`.
6. Submit through [Module Submission](https://cf.pandagenie.ai/sign).

See `source/README.md` for example details.

## License

LGPL-3.0.
