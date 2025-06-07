import os
import subprocess
import zipfile

def run(cmd, cwd=None):
    subprocess.run(cmd, shell=True, check=True, cwd=cwd)

def create_kotlin_spring(project_name):
    run(f"curl https://start.spring.io/starter.zip -d language=kotlin -d type=gradle-project -d dependencies=web,data-jpa,postgresql -d name={project_name} -o {project_name}-backend.zip")
    with zipfile.ZipFile(f"{project_name}-backend.zip", 'r') as zip_ref:
        zip_ref.extractall(f"{project_name}-backend")
    os.remove(f"{project_name}-backend.zip")
    base = f"{project_name}-backend/src/main/kotlin/com/example/{project_name}"
    os.makedirs(f"{base}/application", exist_ok=True)
    os.makedirs(f"{base}/domain", exist_ok=True)
    os.makedirs(f"{base}/infrastructure", exist_ok=True)

def create_angular(project_name):
    run(f"npx -p @angular/cli ng new {project_name}-frontend --skip-git --routing --style=scss")

if __name__ == "__main__":
    project = input("Entrez le nom du projet : ")
    create_kotlin_spring(project)
    create_angular(project)
    print(f"Projet {project} généré avec succès.")