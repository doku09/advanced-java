package io.member;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;
import io.member.impl.ObjectmemberRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberConsoleMain {

//	private static final MemberRepository repository = new MemoryMemberRepository();
//	private static final MemberRepository repository = new FileMemberRepository();
//	private static final MemberRepository repository = new DataMemberRepository();
	private static final MemberRepository repository = new ObjectmemberRepository();

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1.회원 등록 | 2.회원 목록 조회 | 3.종료");
			System.out.print("선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); //newline 제거

			switch (choice) {
				case 1:
					//회원등록
					registerMember(scanner);
					break;
				case 2:
					//회원목록 조회
					displaymembers();
					break;
				case 3:
					System.out.println("프로그램 종료");
					return;
				default:
					System.out.println("잘못된 선택");
			}
		}
	}

	private static void registerMember(Scanner scanner) throws IOException {
		System.out.print("ID 입력: ");
		String id = scanner.nextLine();
		System.out.println("Name 입력: ");
		String name = scanner.nextLine();
		System.out.println("Age 입력: ");
		int age = scanner.nextInt();
		scanner.nextLine();

		Member newMember = new Member(id, name, age);
		repository.add(newMember);
		System.out.println("회원 등록 완!");
	}

	private static void displaymembers() {
		List<Member> members = repository.findAll();
		System.out.println("회원목록: ");
		for (Member member : members) {
			System.out.printf("[ID: %s, Name: %s, Age: %d]\n", member.getId(),member.getName(),member.getAge());
		}
	}
}
