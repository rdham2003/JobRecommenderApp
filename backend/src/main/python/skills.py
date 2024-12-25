with open("skills.csv", 'r', encoding="UTF-8") as f:
    skills = f.readlines()
    print(skills)
    skills = set(skills)
    print(len(skills))
    
with open("new_skills.csv", 'w', encoding="UTF-8") as f:
    for skill in skills:
        f.write(f'{skill}\n')