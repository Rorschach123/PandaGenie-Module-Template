<div align="center">

# 🐼 PandaGenie Module Template

**Start building your own PandaGenie module in minutes**

[![Use this template](https://img.shields.io/badge/Use%20this-Template-6c5ce7?style=for-the-badge)](https://github.com/Rorschach123/PandaGenie-Module-Template/generate)

</div>

---

## Quick Start

### 1. Use This Template

Click **"Use this template"** button above (or on GitHub) to create your own module repository.

### 2. Customize Your Module

Rename and edit the files in `source/my_module/`:

| File | What to do |
|------|-----------|
| `manifest.json` | Change `id`, `name`, `description`, and define your `apis` |
| `plugin_src/.../MyModulePlugin.java` | Implement your logic in the `invoke()` method |
| `index.html` | (Optional) Create a settings/config UI page |

### 3. Build & Test

```powershell
# Clone PandaGenieModules for the build toolkit
git clone https://github.com/Rorschach123/PandaGenieModules.git

# Copy your module source into PandaGenieModules' parent directory
# Or symlink: your source/my_module → PandaGenieSource/source/my_module

cd PandaGenieModules/module-dev-toolkit/

# Generate your developer signing key (first time only)
.\mk_module.ps1 -Action init-dev-signing

# Build your module
.\mk_module.ps1 -Action pack -Modules "my_module"

# Push to device
adb push ..\modules\my_module.mod /sdcard/PandaGenie/modules/
```

### 4. Test on Device

1. Open PandaGenie app → Settings → Enable **Developer Mode**
2. Restart the app
3. Your module is now loaded! Try chatting:
   - *"Test my module"*
   - *"Say hello to Alice"*

### 5. Publish

When your module is ready:

- **Option A (Recommended):** Upload to [cf.pandagenie.ai](https://cf.pandagenie.ai) for automatic signing & publishing
- **Option B:** Submit a PR to [PandaGenieSource](https://github.com/Rorschach123/PandaGenieSource)

---

## Module Structure

```
source/my_module/
├── manifest.json                                    ← Module metadata & API definitions
├── index.html                                       ← Optional UI page (WebView)
└── plugin_src/
    └── ai/rorsch/moduleplugins/my_module/
        └── MyModulePlugin.java                      ← Your plugin logic
```

## The Only Interface You Need

```java
public interface ModulePlugin {
    String invoke(Context context, String action, String paramsJson) throws Exception;
}
```

- `action` — which API is being called (matches `apis[].name` in manifest.json)
- `paramsJson` — JSON string with parameters (matches `apis[].params` in manifest.json)
- Return a JSON string: `{"success": true, "output": "..."}` or `{"success": false, "error": "..."}`

## Writing Good API Descriptions

The AI reads your `manifest.json` to understand what your module can do. Good descriptions = better AI behavior.

**Good example:**
```json
{
  "name": "searchFiles",
  "desc": "在指定目录下搜索文件，支持通配符匹配。当用户说【找文件】【搜索XX文件】时使用",
  "desc_en": "Search files in a directory with wildcard support. Use when user says 'find files' or 'search for XX'",
  "params": ["dir", "pattern"],
  "paramDesc": ["搜索目录路径", "文件名匹配模式，如 *.jpg"],
  "paramDesc_en": ["Directory path to search", "Filename pattern, e.g. *.jpg"]
}
```

**Bad example:**
```json
{
  "name": "search",
  "desc": "搜索",
  "params": ["q"]
}
```

## Resources

- [Full Module Development Guide](https://github.com/Rorschach123/PandaGenieModules/blob/main/module-dev-toolkit/MODULE_DEVELOPMENT_GUIDE.md)
- [PandaGenie Source Code](https://github.com/Rorschach123/PandaGenieSource)
- [Module Marketplace](https://github.com/Rorschach123/PandaGenieModules)
- [Discord Community](https://discord.gg/Cfc7pjrjt2)

---

## License

LGPL-3.0 — See [LICENSE](LICENSE) for details.
