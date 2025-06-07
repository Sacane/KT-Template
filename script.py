import os
import subprocess
import shutil


def run(cmd, cwd=None):
    subprocess.run(cmd, shell=True, check=True, cwd=cwd)

def replace_in_file(filepath, replacements):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        for old, new in replacements.items():
            content = content.replace(old, new)
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)

def replace_in_tree(root, replacements):
    for dirpath, _, filenames in os.walk(root):
        for filename in filenames:
            filepath = os.path.join(dirpath, filename)
            try:
                replace_in_file(filepath, replacements)
            except Exception:
                pass

def move_kotlin_package(src_root, old_package, new_package):
    old_path = os.path.join(src_root, *old_package.split('.'))
    new_path = os.path.join(src_root, *new_package.split('.'))
    if os.path.exists(old_path):
        os.makedirs(os.path.dirname(new_path), exist_ok=True)
        shutil.move(old_path, new_path)
        parent = os.path.dirname(old_path)
        while parent != src_root and os.path.isdir(parent) and not os.listdir(parent):
            os.rmdir(parent)
            parent = os.path.dirname(parent)

def copy_template(template_dir, dest_dir):
    if os.path.exists(dest_dir):
        shutil.rmtree(dest_dir)
    shutil.copytree(template_dir, dest_dir)

def main():
    project = input("Project name: ").strip()
    if not project:
        project = 'MyProject'
    package = input("Main package (e.g. com.business.sacane): ").strip()
    if not package:
        package = 'com.example.app'
    dest_root = input("Destination folder path (e.g. /home/user/projects): ").strip()
    if not dest_root:
        dest_root = os.getcwd()
    template_dir = os.path.join(os.path.dirname(__file__), 'Template')
    dest_dir = os.path.join(dest_root, project)
    copy_template(template_dir, dest_dir)
    replacements = {
        'com.template': package,
        'com/template': package.replace('.', '/'),
        'Template': project,
        'template': project[0].lower() + project[1:],
    }
    replace_in_tree(dest_dir, replacements)
    src_root = os.path.join(dest_dir, 'domain', 'src', 'main', 'kotlin')
    move_kotlin_package(src_root, 'com.template', package)
    src_root = os.path.join(dest_dir, 'infrastructure', 'src', 'main', 'kotlin')
    move_kotlin_package(src_root, 'com.template', package)
    print(f"Project {project} has been created in {dest_dir} with package {package}.")

if __name__ == "__main__":
    main()
