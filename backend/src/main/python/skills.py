with open("skills.csv", 'r', encoding="UTF-8") as f:
    skills = f.readlines()
    # print(skills)
    skills = set(skills)
    print(len(skills))
    skills = [skill[0:-1].lower() for skill in skills]
    skills = sorted(skills)
    print(len(skills))
    print(skills)
with open("new_skills.csv", 'w') as f:
    for skill in skills:
        f.write(f'{skill}\n')