USE clearlinkjournal;

insert into users (first_name, last_name, username, password)
values ('Jon', 'Lancaster', 'jonlan', 'pass'),
  ('Fernando', 'Salazar', 'fersal', 'ferSal'),
  ('Scuba', 'Steve', 'steve', 'passwd'),
  ('Fred', 'Rogers', 'mrrogers', 'neighborhood'),
  ('Big', 'Bird', 'bigbird', 'yellow'),
  ('Oscar', 'Grouch', 'grouchy', 'garbage'),
  ('The', 'Count', 'thecount', 'ILoveToCount'),
  ('Cookie', 'Monster', 'cookies', 'cookies');

insert into user_roles (username, role)
values ('jonlan', 'ROLE_ADMIN'),
  ('jonlan', 'ROLE_COACH'),
  ('jonlan', 'ROLE_USER'),
  ('fersal', 'ROLE_ADMIN'),
  ('fersal', 'ROLE_COACH'),
  ('fersal', 'ROLE_USER'),
  ('steve', 'ROLE_COACH'),
  ('steve', 'ROLE_USER'),
  ('mrrogers', 'ROLE_COACH'),
  ('mrrogers', 'ROLE_USER'),
  ('bigbird', 'ROLE_USER'),
  ('grouchy', 'ROLE_USER'),
  ('thecount', 'ROLE_USER'),
  ('cookies', 'ROLE_USER');

insert into journals (journal_name, user_id)
values ('Jons Journal', 1),
  ('fernandos journal', 2),
  ('scubas journal', 3),
  ('freds journal', 4),
  ('bigs journal', 5),
  ('oscars journal', 6),
  ('thes journal', 7),
  ('cookies journal', 8);

insert into journal_entries (journal_id, positive_review, goal, momentum, mental_toughness, willingness, determination, motivation, attitude, personal_impact)
values (1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1);


