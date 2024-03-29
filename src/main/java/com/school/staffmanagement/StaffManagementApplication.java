package com.school.staffmanagement;

import com.school.staffmanagement.model.entity.PermissionEntity;
import com.school.staffmanagement.model.entity.RoleEntity;
import com.school.staffmanagement.model.entity.UserEntity;
import com.school.staffmanagement.model.enums.RoleEnum;
import com.school.staffmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class StaffManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// CREACION DE PERMISOS
			PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
			PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
			PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
			PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();

			// CREACION DE ROLES
			RoleEntity adminRole = RoleEntity.builder().name(RoleEnum.ADMIN).permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission)).build();
			RoleEntity userRole = RoleEntity.builder().name(RoleEnum.USER).permissions(Set.of(createPermission, readPermission)).build();
			RoleEntity guestRole = RoleEntity.builder().name(RoleEnum.GUEST).permissions(Set.of(readPermission)).build();
			RoleEntity devRole = RoleEntity.builder().name(RoleEnum.DEVELOPER).permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission)).build();

			// CREACION DE USUARIOS
			UserEntity userAdri = UserEntity.builder()
					.email("adri@test.com").password("$2a$10$5QSu0mxbsibEXRwI8CkFE.je0/oKwYK.Zz4lpYnTu1nfB0gRLreYa")
					.isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true)
					.roles(Set.of(adminRole, devRole))
					.build();

			UserEntity userPepa = UserEntity.builder()
					.email("pepa@test.com").password("$2a$10$5QSu0mxbsibEXRwI8CkFE.je0/oKwYK.Zz4lpYnTu1nfB0gRLreYa")
					.isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true)
					.roles(Set.of(userRole))
					.build();

			UserEntity userFulano = UserEntity.builder()
					.email("fulano@test.com").password("$2a$10$5QSu0mxbsibEXRwI8CkFE.je0/oKwYK.Zz4lpYnTu1nfB0gRLreYa")
					.isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true)
					.roles(Set.of(guestRole))
					.build();

			userRepository.saveAll(List.of(userFulano, userAdri, userPepa));
		};
	}

}
